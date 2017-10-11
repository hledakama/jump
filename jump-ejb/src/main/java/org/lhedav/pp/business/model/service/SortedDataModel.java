/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lhedav.pp.business.model.service;

import java.util.Arrays;
import java.util.Comparator;
import javax.faces.model.CollectionDataModel;

/**
 *
 * @author client
 */
public class SortedDataModel<T> extends CollectionDataModel<T> {
              private CollectionDataModel<T> m_model;
           
           private Integer[] rows;
           public SortedDataModel(CollectionDataModel<T> model) {
              this.m_model = model;
              initRows();
        }
            private void initRows() {
                if(m_model == null) return;
            int rowCount = m_model.getRowCount();
            if (rowCount != -1) {
                this.rows = new Integer[rowCount];
                for (int i = 0; i < rowCount; ++i) 
                {
                    rows[i] = i;
                }
                }
            }
            
            
        public void sortThis(final Comparator<T> comparator) {
            Comparator<Integer> rowc = new Comparator<Integer>() {
            @Override
            public int compare(Integer key_1, Integer key_2) {
            T key_1_data = getData(key_1);
            T key_2_data = getData(key_2);
            return comparator.compare(key_1_data, key_2_data);
            }
            };
            Arrays.sort(rows, rowc);
         }
    
        public T getData(int row) {
            if( m_model == null) return null;
            int baseRowIndex = m_model.getRowIndex();
            m_model.setRowIndex(row);
            T newRowData = m_model.getRowData();
            m_model.setRowIndex(baseRowIndex);
            return newRowData;
        } 
        
         @Override
    public void setRowIndex(int rowIndex) {
        if(rows == null || m_model == null) return;
        if(rows == null || m_model == null) return;
        if ((0 <= rowIndex) && (rowIndex < rows.length)) {
            m_model.setRowIndex(rows[rowIndex]);
        } else {
            m_model.setRowIndex(rowIndex);
        }
    }

    @Override
    public boolean isRowAvailable() {
        if( m_model == null) return false;
        return m_model.isRowAvailable();
    }

    @Override
    public int getRowCount() {
        if(m_model == null) return 0;
        return m_model.getRowCount();
    }

    @Override
    public T getRowData() {
        if(m_model == null) return null;
        return m_model.getRowData();
    }

    @Override
    public int getRowIndex() {
        if( m_model == null) return -1;
        return m_model.getRowIndex();
    }

    @Override
    public Object getWrappedData() {
        if(m_model == null) return null;
        return m_model.getWrappedData();
    }

    @Override
    public void setWrappedData(Object data) {       
    } 
    
    public CollectionDataModel<T> getModel(){
        return m_model;
    }
}
