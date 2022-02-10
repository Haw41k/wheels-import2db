package haw41k.wheels.import2db.adapters;

public class FileAdapterBuffalo extends FileAdapter {
    public FileAdapterBuffalo() {
        super.brandName = "BUFFALO";
        super.indexFirstRow = 10;

        super.indexModel = 2;

        super.indexSizeDiameter = 4;
        super.indexSizeWidth = 3;

        super.indexPcdCount = 5;
        super.indexPcdDiameter = 6;

        super.indexEt = 7;
        super.indexDia = 8;

        super.indexColor = 9;
        super.indexFactoryCode = 10;
    }
}
