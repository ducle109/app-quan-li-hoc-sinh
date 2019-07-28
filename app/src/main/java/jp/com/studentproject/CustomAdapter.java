package jp.com.studentproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int layout;
    private List<Student> listStudent;

    public CustomAdapter(Context context, int layout, List<Student> objects) {
        super(context, layout, objects);
        this.context = context;
        this.layout = layout;
        this.listStudent = objects;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        // ViewHolderを作ります
        ViewHolder viewHolder;
        // View＝Nullの場合は新しいviewを作る（1 view = 1 dòng）
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.show_all_student, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvId                         = (TextView)view.findViewById(R.id.tv_id);
            viewHolder.tvName                       = (TextView)view.findViewById(R.id.tv_name);
            viewHolder.tvAge                        = (TextView)view.findViewById(R.id.tv_age);
            viewHolder.tvSex                        = (TextView)view.findViewById(R.id.tv_sex);
            viewHolder.tvPhone                     = (TextView)view.findViewById(R.id.tv_phone);

            // 作ったviewはViewHolderに渡します。
            view.setTag(viewHolder);
        } else {
            // ViewHolderがあった場合はそのまま表示させる。
            viewHolder = (ViewHolder) view.getTag();
        }
        // Classから値を入れる。
        Student student = listStudent.get(position);
        viewHolder.tvId.setText(String.valueOf(student.getId()));
        viewHolder.tvName.setText(student.getName());
        viewHolder.tvAge.setText(student.getAge() + "");
        viewHolder.tvSex.setText(student.getSex() + "");
        viewHolder.tvPhone.setText(student.getPhoneNumber() + "");

        // 値を返す。
        return view;
    }

    public class ViewHolder {
        private TextView tvId;
        private TextView tvName;
        private TextView tvAge;
        private TextView tvSex;
        private TextView tvPhone;
    }

}
