package com.example.android6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ItemDetails extends AppCompatActivity {
  private int presidentIndex, presidentIndexPrev;
  private President president;
  private GlobalModel global;
  private boolean editing;
  private TextView name, startYear, endYear, description, indexHeader, errorMessage;
  private EditText nameEdit, startYearEdit, endYearEdit, descriptionEdit;
  private Button editButton, saveButton, cancelButton, removeButton;
  private Button moveUpButton, moveDownButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_item_details);

    setReturnArrow();
    locateUIElements();
    initUIElementVisibility();
    findPresident();
    printInformation();
  }

  @Override
  public boolean onSupportNavigateUp(){
    stopEditing();
    if(emptyPresident()){
      global.removePresident(presidentIndex);
    }
    finish();
    return true;
  }

  @Override
  public void onBackPressed(){
    if(emptyPresident()){
      global.removePresident(presidentIndex);
    }
    super.onBackPressed();
  }

  private void setReturnArrow(){
    assert getSupportActionBar() != null;
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void locateUIElements(){
    name        = findViewById(R.id.name);
    startYear   = findViewById(R.id.startYear);
    endYear     = findViewById(R.id.endYear);
    description = findViewById(R.id.description);

    nameEdit        = findViewById(R.id.presidentNameEdit);
    startYearEdit   = findViewById(R.id.presidentStartYearEdit);
    endYearEdit     = findViewById(R.id.presidentEndYearEdit);
    descriptionEdit = findViewById(R.id.presidentDescriptionEdit);

    editButton   = findViewById(R.id.editButton);
    saveButton   = findViewById(R.id.saveButton);
    cancelButton = findViewById(R.id.cancelButton);
    removeButton = findViewById(R.id.removeButton);

    indexHeader    = findViewById(R.id.indexHeader);
    errorMessage   = findViewById(R.id.errorMessage);
    moveUpButton   = findViewById(R.id.moveUp);
    moveDownButton = findViewById(R.id.moveDown);
  }

  private void initUIElementVisibility(){
    setEditMode(false);
  }

  private void findPresident(){
    Bundle b = getIntent().getExtras();
    presidentIndex = b.getInt(MainActivity.EXTRA,0);
    presidentIndexPrev = presidentIndex;
    global = GlobalModel.getInstance();
    president = global.getPresident(presidentIndex);
  }

  private void printInformation(){
    name.setText(president.getName());
    startYear.setText(president.getStartYear());
    endYear.setText(president.getEndYear());
    description.setText(president.getDescription());
    if(emptyPresident()){
      printIndexHeader();
      setEditMode(true);
    }
  }

  private boolean emptyPresident(){
    return  president.getName().equals("") &&
            president.getStartYear().equals("") &&
            president.getEndYear().equals("") &&
            president.getDescription().equals("");
  }

  private void setEditMode(boolean edit){
    editing = !edit;
    toggleEditMode();
  }

  private void toggleEditMode(){
    if(editing) {
      setEditTextVisibility(View.INVISIBLE);
      setTextViewVisibility(View.VISIBLE);
      editButton.setVisibility(View.VISIBLE);
      indexHeader.setVisibility(View.INVISIBLE);
      setEditingButtonVisibility(View.INVISIBLE);
      editing = false;
    } else {
      setEditTextVisibility(View.VISIBLE);
      setTextViewVisibility(View.INVISIBLE);
      editButton.setVisibility(View.INVISIBLE);
      indexHeader.setVisibility(View.VISIBLE);
      setEditingButtonVisibility(View.VISIBLE);
    }
  }

  private void printIndexHeader(){
    String setHeader = "Tämä presidentti on listassa sijalla " +
                       (presidentIndex+1) + "/" +
                       global.getPresidents().size();
    indexHeader.setText(setHeader);
  }

  public void startEditing(View caller){
    setEditMode(true);
    printIndexHeader();
    nameEdit.setText(president.getName());
    startYearEdit.setText(president.getStartYear());
    endYearEdit.setText(president.getEndYear());
    descriptionEdit.setText(president.getDescription());
  }

  public void saveEdits(View caller){
    president.setName(nameEdit.getText().toString());
    president.setStartYear(startYearEdit.getText().toString());
    president.setEndYear(endYearEdit.getText().toString());
    president.setDescription(descriptionEdit.getText().toString());
    presidentIndexPrev = presidentIndex;
    stopEditing();
    printInformation();
    String msg = "";
    if(emptyPresident()){
      msg = "Syötä vähintään yhteen kenttään tieto!";
    }
    errorMessage.setText(msg);
  }

  public void stopEditing(View caller){
    setEditMode(false);
    if(emptyPresident()){
      global.removePresident(presidentIndex);
      finish();
    } else {
      moveBackToPreviousPlace();
    }
  }

  private void stopEditing(){
    moveBackToPreviousPlace();
    setEditMode(false);
  }

  private void moveBackToPreviousPlace(){
    while(presidentIndexPrev > presidentIndex){
      global.movePresidentUp(presidentIndex);
      presidentIndex++;
    }
    while(presidentIndexPrev < presidentIndex){
      global.movePresidentDown(presidentIndex);
      presidentIndex--;
    }
  }

  public void removeItem(View caller){
    global.removePresident(presidentIndex);
    stopEditing();
    super.finish();
  }

  private void setTextViewVisibility(int state){
    name.setVisibility(state);
    startYear.setVisibility(state);
    endYear.setVisibility(state);
    description.setVisibility(state);
  }

  private void setEditTextVisibility(int state){
    nameEdit.setVisibility(state);
    startYearEdit.setVisibility(state);
    endYearEdit.setVisibility(state);
    descriptionEdit.setVisibility(state);
  }

  private void setEditingButtonVisibility(int state){
    saveButton.setVisibility(state);
    cancelButton.setVisibility(state);
    removeButton.setVisibility(state);
    moveUpButton.setVisibility(state);
    moveDownButton.setVisibility(state);
  }

  public void moveUp(View caller){
    presidentIndex = global.movePresidentUp(presidentIndex);
    printIndexHeader();
  }

  public void moveDown(View caller){
    presidentIndex = global.movePresidentDown(presidentIndex);
    printIndexHeader();
  }
}
