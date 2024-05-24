package ru.mirea.tmenovapa.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataFragment newInstance(String param1, String param2) {
        DataFragment fragment = new DataFragment();
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
        // Настройка отображения данных о выбранной отрасли
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        TextView MachineLearningInfoTextView = view.findViewById(R.id.InfoTextView);
        String MachineLearningInfo = "Уникальная информация о машинном обучении здесь...\n\n" +
                "Машинное обучение (Machine Learning, ML) - это раздел искусственного интеллекта, который изучает методы построения систем, способных обучаться на основе предоставленных данных и делать прогнозы или принимать решения на основе этого обучения. В ML используются различные алгоритмы и модели, такие как нейронные сети, деревья решений, метод ближайших соседей и другие, для извлечения закономерностей из данных и прогнозирования результатов.\n\n" +
                "Применения машинного обучения включают в себя распознавание образов, анализ текста, рекомендательные системы, автоматическое управление, медицинскую диагностику, финансовый анализ и многое другое.\n\n" +
                "Основные задачи машинного обучения включают в себя классификацию, регрессию, кластеризацию и обучение с подкреплением.\n\n";

        MachineLearningInfoTextView .setText(MachineLearningInfo);

        return view;
    }
}