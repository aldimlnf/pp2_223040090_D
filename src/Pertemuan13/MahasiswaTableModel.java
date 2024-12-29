package srcmodul13;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MahasiswaTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "NIM", "Nilai"};
    private ArrayList<Mahasiswa> data = new ArrayList<>();

    public void add(Mahasiswa value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Mahasiswa mahasiswa = data.get(row);
        switch (col) {
            case 0: return mahasiswa.getNama();
            case 1: return mahasiswa.getNim();
            case 2: return mahasiswa.getNilai();
            default: return null;
        }
    }
}