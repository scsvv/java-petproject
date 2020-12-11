package ua.cours.classes.IFace;

import ua.cours.Main;
import ua.cours.classes.task.MemoryScheduler;
import ua.cours.classes.task.Process;
import ua.cours.classes.task.Queue;
import ua.cours.classes.utils.Timer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;


public class Controller {
    @FXML
    private void initialize()
    {
        queueTable.getColumns().setAll(TableColumnGenerator(false));
        rejectedTable.getColumns().setAll(TableColumnGenerator(false));
        doneTable.getColumns().setAll(TableColumnGenerator(false));
        CoresTable.getColumns().setAll(TableColumnGenerator(true));
    }

    //TODO: Show CPU INFO

    @FXML
    TableView queueTable;
    @FXML
    TableView rejectedTable;
    @FXML
    TableView doneTable;
    @FXML
    TableView CoresTable;

    ObservableList<Process> QueueTableList = FXCollections.observableArrayList();
    ObservableList<Process> RejectedTableList = FXCollections.observableArrayList();
    ObservableList<Process> DoneTableList = FXCollections.observableArrayList();
    ObservableList<CoreInfo> CoreITableList = FXCollections.observableArrayList();

    private ArrayList<TableColumn> TableColumnGenerator( boolean coreTable) {

        ArrayList<TableColumn> _tmp = new ArrayList<>();
        if(!coreTable) {
            TableColumn<Process, String> idColumn = new TableColumn<>("ID");
            TableColumn<Process, String> nameColumn = new TableColumn<>("Name");
            TableColumn<Process, String> statusColumn = new TableColumn<>("Status");
            TableColumn<Process, String> tickColumn = new TableColumn<>("TickWorks");
            TableColumn<Process, String> memColumn = new TableColumn<>("Memory");
            TableColumn<Process, String> timeInColumn = new TableColumn<>("TimeIn");
            TableColumn<Process, String> burstColumn = new TableColumn<>("BursTime");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            tickColumn.setCellValueFactory(new PropertyValueFactory<>("tickWorks"));
            memColumn.setCellValueFactory(new PropertyValueFactory<>("memory"));
            timeInColumn.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
            burstColumn.setCellValueFactory(new PropertyValueFactory<>("bursTime"));

            tickColumn.setVisible(false);
            memColumn.setVisible(false);
            burstColumn.setVisible(false);

            _tmp.add(idColumn);
            _tmp.add(nameColumn);
            _tmp.add(statusColumn);
            _tmp.add(tickColumn);
            _tmp.add(memColumn);
            _tmp.add(timeInColumn);
            _tmp.add(burstColumn);
            return _tmp;
        }
        else
        {
            TableColumn<CoreInfo, String> coreNumb = new TableColumn<>("CoreN");
            TableColumn<CoreInfo, String> coreID = new TableColumn<>("PID");
            TableColumn<CoreInfo, String> statusColumn = new TableColumn<>("Status");
            TableColumn<CoreInfo, String> timeInColumn = new TableColumn<>("TimeIn");
            TableColumn<CoreInfo, String> burstColumn = new TableColumn<>("BursTime");

            coreNumb.setCellValueFactory(new PropertyValueFactory<>("name"));
            coreID.setCellValueFactory(new PropertyValueFactory<>("id"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            timeInColumn.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
            burstColumn.setCellValueFactory(new PropertyValueFactory<>("bursTime"));

            _tmp.add(coreNumb);
            _tmp.add(coreID);
            _tmp.add(statusColumn);
            _tmp.add(timeInColumn);
            _tmp.add(burstColumn);
            return _tmp;
        }
    }

    public void TableRowSetter(Queue queue, ArrayList<CoreInfo> coreInfos)
    {
        QueueTableList.setAll(queue.getReadyQueue());
        queueTable.setItems(QueueTableList);
        queueTable.refresh();

        RejectedTableList.setAll(queue.getRejectedQueue());
        rejectedTable.setItems(RejectedTableList);
        rejectedTable.refresh();

        DoneTableList.setAll(queue.getDoneProcesses());
        doneTable.setItems(DoneTableList);
        doneTable.refresh();

        CoreITableList.setAll(coreInfos);
        CoresTable.setItems(CoreITableList);
        CoresTable.refresh();
    }


    @FXML
    Button runBTN;
    @FXML
    Button pauseBTN;
    @FXML
    Button resetBTN;

    @FXML
    protected void runBTN_Click()
    {

        if(!Main.emuThread.isAlive())
            Main.emuThread.start();
        else
            Main.emuThread.resume();

        runBTN.setDisable(true);
        resetBTN.setDisable(true);
        pauseBTN.setDisable(false);
    }

    @FXML
    protected void pauseBTN_Click()
    {
        if(Main.emuThread.isAlive())
            Main.emuThread.suspend();

        pauseBTN.setDisable(true);
        runBTN.setDisable(false);
        resetBTN.setDisable(false);
    }

    @FXML
    protected void resetBTN_Click()
    {

        if(Main.emuThread.isAlive()) {
            Main.emuThread.stop();
        }
        MemoryScheduler.clearMem();
        Timer.clearTime();
        Main.emuThread = new Thread(new ThreadStarter());


        queueTable.setItems(null);
        rejectedTable.setItems(null);
        doneTable.setItems(null);

        resetBTN.setDisable(true);
        pauseBTN.setDisable(true);
        runBTN.setDisable(false);
    }

}
