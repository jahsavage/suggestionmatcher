package com.discoid.test.autocomplete.composer.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.discoid.test.autocomplete.R;
import com.discoid.test.autocomplete.composer.di.ComposerModule;
import com.discoid.test.autocomplete.composer.presenter.ComposerPresenter;
import com.discoid.test.autocomplete.di.inject.InjectHelper;
import com.discoid.test.autocomplete.textmatcher.view.TextMatcherActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class ComposerActivity extends AppCompatActivity implements ComposerView {

    @BindView(R.id.suggestions_list)
    EditText mSuggestionList;

    @BindView(R.id.selectedText)
    TextView mSelectedText;

    @BindView(R.id.complete)
    Button mCompleted;

    @Inject
    ComposerPresenter mComposerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composer);

        butterknife.ButterKnife.bind(this);

        mSuggestionList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mComposerPresenter.beingEdited();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mComposerPresenter.complete(mSuggestionList.getText().toString());
            }
        });

        inject();

        setTitle(R.string.suggestionComposer);
    }

    private void inject() {
        InjectHelper.getApplicationComponent(this).plus(new ComposerModule(this))
                .inject(this);
    }

    public void onStart() {
        super.onStart();
        mComposerPresenter.start(this);
    }

    @Override
    public void makeChooseRequest(String[] suggestions) {
        Intent intent = new Intent(this, TextMatcherActivity.class);
        intent.setAction(Intent.ACTION_PICK);
        intent.putExtra(Intent.EXTRA_TEXT, suggestions);
        intent.setType("text/plain");
        startActivityForResult(intent, TextMatcherActivity.PICK_A_SUGGESTION);
    }

    @Override
    public void clearMessage() {
        mSelectedText.setText("");
    }

    @Override
    public void showNotAValidSuggestionList() {
        mSelectedText.setText(R.string.composer_list_error);
    }

    @Override
    public void showPickedMessage(String pickedString) {
        String pickedMsgFormat = getString(R.string.picked_msg_formatter);
        mSelectedText.setText(String.format(pickedMsgFormat,pickedString));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TextMatcherActivity.PICK_A_SUGGESTION) {

            if (resultCode == RESULT_OK) {
                mComposerPresenter.picked(data.getStringExtra(Intent.EXTRA_TEXT));
            } else {
                mComposerPresenter.cancelledPick();
            }
        }
    }

    @Override
    public void onDestroy() {
        mComposerPresenter.stop();
        super.onDestroy();
    }
}
