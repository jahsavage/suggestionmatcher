package com.discoid.test.autocomplete.textmatcher.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.discoid.test.autocomplete.R;
import com.discoid.test.autocomplete.di.inject.InjectHelper;
import com.discoid.test.autocomplete.textmatcher.di.TextMatcherModule;
import com.discoid.test.autocomplete.textmatcher.presenter.TextMatcherPresenter;

import javax.inject.Inject;

public class TextMatcherActivity extends AppCompatActivity implements TextMatcherView {

    public static final int PICK_A_SUGGESTION = 45;

    @butterknife.BindView(R.id.autocomplete)
     android.widget.AutoCompleteTextView mAutoCompleteTv;

    @Inject
    TextMatcherPresenter mTextMatcherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textmatcher);

        butterknife.ButterKnife.bind(this);

        inject();

        // Match from 2nd character
        mAutoCompleteTv.setThreshold(2);
        mAutoCompleteTv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mTextMatcherPresenter.suggestionSelected(mAutoCompleteTv.getText().toString());
            }
        });

        setTitle(R.string.suggestionMatcher);
    }

    private boolean didWeReceivedData() {
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        String arr[] = intent.getStringArrayExtra(Intent.EXTRA_TEXT);
        return Intent.ACTION_PICK.equals(action) && type.equals("text/plain") && arr != null;
    }

    private void inject() {
        InjectHelper.getApplicationComponent(this).plus(new TextMatcherModule(this))
                .inject(this);
    }

    public void onStart() {
        super.onStart();

        if (didWeReceivedData()) {
            mTextMatcherPresenter.start(this, getIntent().getStringArrayExtra(Intent.EXTRA_TEXT));
        } else {
            mTextMatcherPresenter.start(this);
        }

    }

    public void onDestroy() {
        mTextMatcherPresenter.stop();
        super.onDestroy();
    }

    @Override
    public void loadSuggestions(String[] data) {
        android.widget.ArrayAdapter<String> adapter = new android.widget.ArrayAdapter<String>(this, android.R.layout.select_dialog_item, data);
        mAutoCompleteTv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        mAutoCompleteTv.setThreshold(2);//will start working from first character
    }

    @Override
    public void returnResult(String text) {
        Intent data = new Intent();
        data.putExtra(Intent.EXTRA_TEXT,text);
        setResult(RESULT_OK, data);
        finish();
    }
}
