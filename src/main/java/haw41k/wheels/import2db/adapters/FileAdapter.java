package haw41k.wheels.import2db.adapters;

public abstract class FileAdapter {
    protected int indexModel;

    protected int indexSizeDiameter;
    protected int indexSizeWidth;

    protected int indexPcdCount;
    protected int indexPcdDiameter;

    protected int indexEt;
    protected int indexDia;

    protected int indexColor;
    protected int indexFactoryCode;

    protected String brandName;
    protected int indexFirstRow;

    public int getIndexModel() {
        return indexModel;
    }

    public int getIndexSizeDiameter() {
        return indexSizeDiameter;
    }

    public int getIndexSizeWidth() {
        return indexSizeWidth;
    }

    public int getIndexPcdCount() {
        return indexPcdCount;
    }

    public int getIndexPcdDiameter() {
        return indexPcdDiameter;
    }

    public int getIndexEt() {
        return indexEt;
    }

    public int getIndexDia() {
        return indexDia;
    }

    public int getIndexColor() {
        return indexColor;
    }

    public int getIndexFactoryCode() {
        return indexFactoryCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getIndexFirstRow() {
        return indexFirstRow;
    }
}
