package controller;

import com.sun.applet2.preloader.event.ApplicationExitEvent;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EditorFormController {
    public TextArea txtEditor;
    public AnchorPane pneFind;
    public TextField txtSearch;
    public AnchorPane pneReplace;
    public TextField txtSearch1;
    public TextField txtReplace;
    private int findOffset = -1;
    private final List<Index> searchList = new ArrayList<>();

    public void initialize(){
        pneReplace.setVisible(false);
        pneFind.setVisible(false);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            Pattern regEx = Pattern.compile(newValue);
            Matcher matcher = regEx.matcher(txtEditor.getText());

            searchList.clear();

            while (matcher.find()){
                searchList.add(new Index(matcher.start(), matcher.end() ));
            }
        } );

    }


    public void mnuItemNew_OnAction(ActionEvent actionEvent) {
            txtEditor.clear();
            txtEditor.requestFocus();
    }

    public void mnuItemExit_OnAction(ActionEvent actionEvent) {
    }

    public void mnuItemFind_OnAction(ActionEvent actionEvent) {
        pneFind.setVisible(true);
        txtSearch.requestFocus();
    }

    public void mnuItemReplace_OnAction(ActionEvent actionEvent) {
        pneReplace.setVisible(true);
    }

    public void mnuItemSelectAll_OnAction(ActionEvent actionEvent) {
        txtEditor.selectAll();
    }

    public void btnFindNext_OnAction(ActionEvent actionEvent) {
        if (!searchList.isEmpty()) {
            findOffset++;
            if (findOffset >= searchList.size()) {
                findOffset = 0;
            }
            txtEditor.selectRange(searchList.get(findOffset).startingIndex, searchList.get(findOffset).endIndex);
        }
    }

    public void btnFindPrevious_OnAction(ActionEvent actionEvent) {
        if (!searchList.isEmpty()) {
            findOffset--;
            if (findOffset < 0) {
                findOffset = searchList.size() - 1;
            }
            txtEditor.selectRange(searchList.get(findOffset).startingIndex, searchList.get(findOffset).endIndex);
        }
    }

    public void btnReplace_OnAction(ActionEvent actionEvent) {

    }

    public void btnReplaceAll_OnAction(ActionEvent actionEvent) {
    }
}

class Index{
    int startingIndex;
    int endIndex;

    public Index(int startingIndex, int endIndex) {
        this.startingIndex = startingIndex;
        this.endIndex = endIndex;
    }
}
