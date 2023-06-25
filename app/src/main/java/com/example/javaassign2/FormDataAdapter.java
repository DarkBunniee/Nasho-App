package com.example.javaassign2;

//public class FormDataAdapter {
//}


import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.List;

public class FormDataAdapter extends RecyclerView.Adapter<FormDataAdapter.ViewHolder> {
    private List<FormData> formDataList;

    public FormDataAdapter(List<FormData> formDataList) {
        this.formDataList = formDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_form_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FormData formData = formDataList.get(position);
        holder.txtName.setText(formData.getName());
        holder.txtEmail.setText(formData.getEmail());
    }

    @Override
    public int getItemCount() {
        return formDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
        }
    }
}
