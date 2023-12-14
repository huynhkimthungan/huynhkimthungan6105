package com.example.unlimited_store.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.unlimited_store.R;
import com.example.unlimited_store.adapter.ProductAdapter;
import com.example.unlimited_store.dao.ProductDAO;
import com.example.unlimited_store.model.Product;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoffeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoffeeFragment extends Fragment {
    private RecyclerView rcvCoffee;
    SearchView svCoffee;
    View view;
    ProductDAO productDAO;
    ArrayList<Product> list;
    ProductAdapter productAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CoffeeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Coffee.
     */
    // TODO: Rename and change types and number of parameters
    public static CoffeeFragment newInstance(String param1, String param2) {
        CoffeeFragment fragment = new CoffeeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * Hiển thị RecyclerView
         */
        view = inflater.inflate(R.layout.fragment_coffee, container, false);
        rcvCoffee = view.findViewById(R.id.rcvCoffee);

        productDAO = new ProductDAO(getContext());
        list = new ArrayList<>();
        list = productDAO.getAllCoffee();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rcvCoffee.setLayoutManager(gridLayoutManager);

        productAdapter = new ProductAdapter(getContext(),list,productDAO);
        rcvCoffee.setAdapter(productAdapter);

        /**
         * Search Products
         */
        svCoffee = view.findViewById(R.id.svCoffee);
        svCoffee.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Product> list1 = new ArrayList<>();
                for (Product product:productDAO.getAllCoffee()) {//phải gọi hàm
                    if (product.getName().toLowerCase().contains(newText.toLowerCase())){
                        list1.add(product);
                    }
                }
                productAdapter = new ProductAdapter(getContext(),list1,productDAO);
                rcvCoffee.setAdapter(productAdapter);
                return false;
            }
        });
        return view;
    }
}