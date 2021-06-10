package com.example.employeelist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Employee> employees;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.inflater = LayoutInflater.from(context);
        this.employees = employees;
    }


    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_employee,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(EmployeeAdapter.ViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.nameView.setText("Имя: "+employee.getName());
        holder.phoneNumberView.setText("Тел.: "+String.valueOf(employee.getPhoneNumber()));
        holder.skillsView.setText("Навыки: "+String.valueOf(employee.getSkills()));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, phoneNumberView, skillsView;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.name);
            phoneNumberView = view.findViewById(R.id.phone_number);
            skillsView = view.findViewById(R.id.skills);
        }
    }
}
