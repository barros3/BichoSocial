package com.barros.shark74.bichosocial.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.barros.shark74.bichosocial.R;
import com.barros.shark74.bichosocial.adapter.ContatoAdatpter;
import com.barros.shark74.bichosocial.connection.AppConfig;
import com.barros.shark74.bichosocial.connection.AppController;
import com.barros.shark74.bichosocial.connection.SQLiteHandler;
import com.barros.shark74.bichosocial.connection.SessionManager;
import com.barros.shark74.bichosocial.model.NavDrawerItem;
import com.barros.shark74.bichosocial.model.User;
import com.barros.shark74.bichosocial.util.Constants;
import com.orm.SugarDb;
import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.orm.SugarRecord.findAll;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FriendsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendsFragment extends Fragment {
    public FriendsFragment() {
        // Required empty public constructor
    }

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

//    protected RadioButton mLinearLayoutRadioButton;
//    protected RadioButton mGridLayoutRadioButton;

    protected RecyclerView mRecyclerView;
    protected ContatoAdatpter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<User> mDataset;
    SQLiteHandler db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
//        rootView.setTag(R.layout.fragment_recycle_view_friends);

        db  = new SQLiteHandler(getContext());

        HashMap<String, String> userList = db.getUserDetails();

        inserLista();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.myRecycleview);
//        mRecyclerView.setHasFixedSize(true);
        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mAdapter = new ContatoAdatpter(getActivity(),  getData());
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return rootView;
    }

    SessionManager session;

    private void sair() {
        session.setLogin(false);
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

//    private void initDataset() {
//        mDataset = new User[DATASET_COUNT];
//        for (int i = 0; i < DATASET_COUNT; i++) {
//            mDataset[i] = SugarRecord.findById(User.class, i);
////            if(mDataset.equals(null)){
////                mDataset[0] = new User();
////                mDataset[0].setNome("Luciano");
////                mDataset[0].setSenha("123");
////                mDataset[0].setEmail("mail@mail");
////                mDataset[0].save();
////            }
//        }
//    }

//    public User getData() {
//        User userList = new User();
//        userList.findAll(User.class);
//        if(userList.size() == 0){
//            Toast.makeText(getActivity(), "A lista de usuario está vazia!" , Toast.LENGTH_SHORT);
//            return userList;
//        }
//        return userList;
//    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    public User inserLista(){
        User user = new User();
        user.setNome("te123ste");
        user.setSenha("123ad");
        user.save();
        return user;
    }

    public List<User> getData() {
        List<User> userArray = User.listAll(User.class);
        User user = new User();

//        for (int i = 0; i < userArray.size(); i++){
//            User u  =
//            userArray.get(i).setNome(u.getNome());
//            userArray.get(i).setEmail(u.getEmail());
//            userArray.get(i).setSenha(u.getSenha());
//        }
        return userArray;
    }
}
