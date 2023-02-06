package com.example.oraltest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.app.DialogFragment;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.oraltest.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    DocReader Dr= new DocReader();
    DocWriter Dw = new DocWriter();
    String Store = MainActivity.store;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void change(){
        System.out.println("Change!!!!");
        while(true){
            if(binding.radioButtonPri.isChecked()){
                MainActivity.store="Private.xml";
                MainActivity.RecordData="Private.txt";
                break;
            }else
            if(binding.radioButtonInst.isChecked()){
                MainActivity.store="Instrument.xml";
                MainActivity.RecordData="Instrument.txt";
                break;
            }else
            if(binding.radioButtonCom.isChecked()){
                MainActivity.store="Commercial.xml";
                MainActivity.RecordData="Commercial.txt";
                break;
            }else
            if(binding.radioButtonZXTGp.isChecked()){
                MainActivity.store="ZXTGp.xml";
                MainActivity.RecordData="ZXTGp.txt";
                break;
            }else
            if(binding.radioButtonZXTG.isChecked()){
                MainActivity.store="ZXTG.xml";
                MainActivity.RecordData="ZXTG.txt";
                break;
            }
            break;
        }
    }
    public void update(){
        //update each time
        System.out.println("update!!!!");
        if(MainActivity.store=="Private.xml"){
            binding.radioButtonPri.setChecked(true);
        }else if(MainActivity.store=="Instrument.xml"){
            binding.radioButtonInst.setChecked(true);
        }else if(MainActivity.store=="Commercial.xml") {
            binding.radioButtonCom.setChecked(true);
        }else if(MainActivity.store=="ZXTGp.xml") {
            binding.radioButtonZXTGp.setChecked(true);
        }else if(MainActivity.store=="ZXTG.xml") {
            binding.radioButtonZXTG.setChecked(true);
        }
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        update();
        binding.radioButtonPri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              change();
            }
        });
        binding.radioButtonInst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
            }
        });
        binding.radioButtonCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
            }
        });
        binding.radioButtonZXTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
            }
        });
        binding.radioButtonZXTGp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
            }
        });


        binding.buttonFirst1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        //标记口试题
        binding.buttonFirst2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Dw.readFileData("a.txt",getActivity().getApplicationContext())==null){
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("提示")
                            .setMessage("此处尚未存入题目")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    return;
                                }
                            })
                            .create();

                    alertDialog.show();
                }else{
                    System.out.println("IN");
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_thirdFragment);
                }
            }
        });
        binding.buttonFirst0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Dr.readerXml(Store,"Ques","Question",getActivity().getApplicationContext())[0]==null){
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("提示")
                            .setMessage("此处尚未存入题目")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    return;
                                }
                            })
                            .create();

                    alertDialog.show();
                }else{
                    System.out.println("IN");
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_forthFragment);
                }
            }
        });
        binding.buttonFirst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("退出程序")
                        .setMessage("是否退出程序")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialogInterface, int i) {
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialogInterface, int i) {
                                return;
                            }
                        }).create();
                alertDialog.show();
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}