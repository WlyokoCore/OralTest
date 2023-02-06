package com.example.oraltest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.oraltest.databinding.FragmentSecondBinding;
import com.example.oraltest.databinding.FragmentThirdBinding;

import java.util.ArrayList;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;
    DocReader Dr= new DocReader();
    DocWriter Dw = new DocWriter();
    ArrayList<Integer> MarkR ;
    String[] q;
    String[] a;
    int num= 0;
    int l ;
    int k ;
    String RecordData= MainActivity.RecordData;
    String store = MainActivity.store;




    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void save(ArrayList E){

        Dw.writeFileData(RecordData,E,getActivity().getApplicationContext());

    }

    public void initiate(){
        try{
            MarkR=Dw.readFileData(RecordData,getActivity().getApplicationContext());
            q =(String[])Dr.readerXml(store,"Ques","Question",getActivity().getApplicationContext()).clone();
            a =(String[])Dr.readerXml(store,"Ques","Answer",getActivity().getApplicationContext()).clone();
            l = MarkR.size();
            k = MarkR.size();
            System.out.println("k="+k);
            System.out.println("l="+l);
        }catch (Exception e){

        }




        binding.buttonSecond11.setVisibility(View.VISIBLE);

        binding.buttonSecond12.setVisibility(View.GONE);

        binding.buttonSecond13.setVisibility(View.GONE);

        binding.buttonSecond14.setVisibility(View.GONE);

        binding.textviewSecond3.setVisibility(View.VISIBLE);

        binding.textViewAnswer.setVisibility(View.GONE);
        }




    public void SwitchToAnswer(){

        binding.buttonSecond11.setVisibility(View.GONE);

        binding.buttonSecond12.setVisibility(View.VISIBLE);

        binding.buttonSecond13.setVisibility(View.VISIBLE);

        binding.buttonSecond14.setVisibility(View.VISIBLE);

        binding.textviewSecond3.setVisibility(View.GONE);

        binding.textViewAnswer.setVisibility(View.VISIBLE);

    }
    public void SwitchToQuestions(){

        binding.buttonSecond11.setVisibility(View.VISIBLE);

        binding.buttonSecond12.setVisibility(View.GONE);

        binding.buttonSecond13.setVisibility(View.GONE);

        binding.buttonSecond14.setVisibility(View.GONE);

        binding.textviewSecond3.setVisibility(View.VISIBLE);

        binding.textViewAnswer.setVisibility(View.GONE);

    }
    public void SwitchForwardProblem(){
        num++;
        if((num<l)&&(num<k)){
            if(q[num]!=null){
                binding.textviewSecond3.setText(q[MarkR.get(num)]);
            }
            if(a[num]!=null){
                binding.textViewAnswer.setText(a[MarkR.get(num)]);
            }
        }else{
            num--;
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage("已经是最后一道题了")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    })
                    .create();

            alertDialog.show();
        }

    }
    public void Update(){

        if((num<l)&&(num<k)&&((num>=0)&&(num>=0))){
            if(q[num]!=null){
                binding.textviewSecond3.setText(q[MarkR.get(num)]);
            }
            if(a[num]!=null){
                binding.textViewAnswer.setText(a[MarkR.get(num)]);
            }
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage("已经是最后一道题了")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    })
                    .create();

            alertDialog.show();
        }

    }

    public void SwitchBackwardProblem(){
        num--;
        if((num>=0)&&(num>=0)){
            if(q[num]!=null){
                binding.textviewSecond3.setText(q[MarkR.get(num)]);
            }
            if(a[num]!=null){
                binding.textViewAnswer.setText(a[MarkR.get(num)]);
            }
        }else{
            num++;
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage("已经是第一道题了")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    })
                    .create();

            alertDialog.show();
        }

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initiate();
        try{
            if(q[num]!=null){
                binding.textviewSecond3.setText(q[MarkR.get(num)]);
            }
            if(a[num]!=null){
                binding.textViewAnswer.setText(a[MarkR.get(num)]);
            }

        }catch (Exception e){
            System.out.println("Error");
        }



        binding.buttonSecond11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchToAnswer();
            }
        });
        binding.buttonSecond12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchForwardProblem();
                SwitchToQuestions();
            }
        });
        binding.buttonSecond13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MarkR.size()>=1){
                    MarkR.remove(num);
                    l--;
                    k--;
                    Update();
                    SwitchToQuestions();


                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("移除题目")
                        .setMessage("题目已移除")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialogInterface, int i) {


                                return;
                            }
                        })
                        .create();

                alertDialog.show();

                }else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("还没有题目")
                            .setMessage("还没有题目")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    return;
                                }
                            })
                            .create();

                    alertDialog.show();
                }


            }

        });
        binding.buttonSecond14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchToQuestions();
            }
        });
        binding.buttonSecond15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchBackwardProblem();
                SwitchToQuestions();
            }
        });
    }

    @Override
    public void onDestroyView() {
        save(MarkR);
        super.onDestroyView();
        binding = null;
    }

}