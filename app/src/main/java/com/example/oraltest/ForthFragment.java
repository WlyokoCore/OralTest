package com.example.oraltest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.oraltest.databinding.FragmentForthBinding;

import java.util.ArrayList;
import java.util.List;


public class ForthFragment extends Fragment {

    private FragmentForthBinding binding;
    DocReader Dc= new DocReader();
    DocWriter Dw= new DocWriter();
    ArrayList<Integer> S = new ArrayList<Integer>();
    int num= 0;
    String[]  q;
    String[]  a;
    int l ;
    int k ;
    int[] Rnum;
    int BrushNum;
    String store = MainActivity.store;
    String RecordData = MainActivity.RecordData;




    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentForthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public static int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }



    public void save(ArrayList<Integer> E){
        System.out.println("S when save"+S);
        Dw.writeFileData(RecordData,E,getActivity().getApplicationContext());

    }
    public void initiate(){
        if(Dw.readFileData(RecordData,getActivity().getApplicationContext())==null){
            System.out.println("isEmpty!");

        }else{
            S=Dw.readFileData(RecordData,getActivity().getApplicationContext());
        }

        System.out.println("S when ini"+S);
        q =(String[])Dc.readerXml(store,"Ques","Question",getActivity().getApplicationContext()).clone();
        a =(String[])Dc.readerXml(store,"Ques","Answer",getActivity().getApplicationContext()).clone();
        l = q.length;
        k = a.length;
        System.out.println("k="+k);
        System.out.println("l="+l);
        Rnum= randomCommon(0,l,l-1);
        for(int a= 0;a<Rnum.length;a++){
            System.out.println(Rnum[a]);
        }
        BrushNum =0;

        binding.buttonSecond.setVisibility(View.VISIBLE);

        binding.buttonSecond2.setVisibility(View.GONE);

        binding.buttonSecond3.setVisibility(View.GONE);

        binding.buttonSecond4.setVisibility(View.GONE);

        binding.textviewSecond.setVisibility(View.VISIBLE);

        binding.textViewAnswer.setVisibility(View.GONE);
        binding.textView5.setText("本次已刷"+BrushNum+"题");

    }


    public void SwitchToAnswer(){

            binding.buttonSecond.setVisibility(View.GONE);

            binding.buttonSecond2.setVisibility(View.VISIBLE);

            binding.buttonSecond3.setVisibility(View.VISIBLE);

            binding.buttonSecond4.setVisibility(View.VISIBLE);

            binding.textviewSecond.setVisibility(View.GONE);

            binding.textViewAnswer.setVisibility(View.VISIBLE);

    }
    public void SwitchToQuestions(){

        binding.buttonSecond.setVisibility(View.VISIBLE);

        binding.buttonSecond2.setVisibility(View.GONE);

        binding.buttonSecond3.setVisibility(View.GONE);

        binding.buttonSecond4.setVisibility(View.GONE);

        binding.textviewSecond.setVisibility(View.VISIBLE);

        binding.textViewAnswer.setVisibility(View.GONE);

    }
    public void SwitchForwardProblem(){
        num++;
        if(num<l-1){
            if(q[Rnum[num]]!=null){
                binding.textviewSecond.setText(q[Rnum[num]]);
            }
            if(a[Rnum[num]]!=null){
                binding.textViewAnswer.setText(a[Rnum[num]]);
            }
            BrushNum++;
            binding.textView5.setText("本次已刷"+BrushNum+"题");


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
    public void SwitchBackwardProblem(){
        num--;
        if(num>=0){
            if(q[Rnum[num]]!=null){
                binding.textviewSecond.setText(q[Rnum[num]]);
            }
            if(a[Rnum[num]]!=null){
                binding.textViewAnswer.setText(a[Rnum[num]]);
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
        if(q[Rnum[num]]!=null){
            binding.textviewSecond.setText(q[Rnum[num]]);
        }
        if(a[Rnum[num]]!=null){
            binding.textViewAnswer.setText(a[Rnum[num]]);
        }



        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchToAnswer();
            }
        });
        binding.buttonSecond2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchForwardProblem();
                SwitchToQuestions();
            }
        });
        binding.buttonSecond3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("标记题目")
                        .setMessage("已标记题目")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(S!=null){
                                    S.add(Rnum[num]);
                                    save(S);
                                }else if(S==null){
                                    ArrayList<Integer> S = new ArrayList<Integer>();
                                    S.add(Rnum[num]);
                                    save(S);
                                    System.out.println("s when twice ini"+S);
                                }
                                return;
                            }
                        })
                        .create();

                alertDialog.show();
            }

        });
        binding.buttonSecond4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchToQuestions();
            }
        });
        binding.buttonSecond5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchBackwardProblem();
                SwitchToQuestions();
            }
        });
    }

    @Override
    public void onDestroyView() {
        System.out.println("S when dest"+this.S);
        Dw.readFileData(RecordData,getActivity().getApplicationContext());
        super.onDestroyView();
        binding = null;
    }

}