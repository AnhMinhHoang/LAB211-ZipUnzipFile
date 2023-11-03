/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import data_access.ZipUnzipFileDAO;

/**
 *
 * @author GoldCandy
 */
public class ZipUnzipFileRepository implements IZipUnZipFileRepository {

    @Override
    public void compression() {
        if (!ZipUnzipFileDAO.Instance().compression()) {
            return;
        }
        System.out.println("Successfully");
    }

    @Override
    public void extract() {
        if (!ZipUnzipFileDAO.Instance().extraction()) {
            return;
        }
        System.out.println("Successfully");
    }
}
