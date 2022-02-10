package haw41k.wheels.import2db;

import java.util.Objects;

public class Wheel {
    private String model;

    private byte sizeDiameter;
    private float sizeWidth;

    private byte pcdCount;
    private float pcdDiameter;

    private float et;
    private float dia;

    private String color;

    private int factoryCode;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public byte getSizeDiameter() {
        return sizeDiameter;
    }

    public void setSizeDiameter(byte sizeDiameter) {
        this.sizeDiameter = sizeDiameter;
    }

    public float getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(float sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public byte getPcdCount() {
        return pcdCount;
    }

    public void setPcdCount(byte pcdCount) {
        this.pcdCount = pcdCount;
    }

    public float getPcdDiameter() {
        return pcdDiameter;
    }

    public void setPcdDiameter(float pcdDiameter) {
        this.pcdDiameter = pcdDiameter;
    }

    public float getEt() {
        return et;
    }

    public void setEt(float et) {
        this.et = et;
    }

    public float getDia() {
        return dia;
    }

    public void setDia(float dia) {
        this.dia = dia;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(int factoryCode) {
        this.factoryCode = factoryCode;
    }

    @Override
    public String toString() {
        return "haw41k.wheels.import2db.Wheel{" +
                "model='" + model + '\'' +
                ", sizeDiameter=" + sizeDiameter +
                ", sizeWidth=" + sizeWidth +
                ", pcdCount=" + pcdCount +
                ", pcdDiameter=" + pcdDiameter +
                ", et=" + et +
                ", dia=" + dia +
                ", color='" + color + '\'' +
                ", factoryCode=" + factoryCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wheel wheel = (Wheel) o;
        return sizeDiameter == wheel.sizeDiameter &&
                Float.compare(wheel.sizeWidth, sizeWidth) == 0 &&
                pcdCount == wheel.pcdCount &&
                Float.compare(wheel.pcdDiameter, pcdDiameter) == 0 &&
                Float.compare(wheel.et, et) == 0 &&
                Float.compare(wheel.dia, dia) == 0 &&
                factoryCode == wheel.factoryCode &&
                model.equals(wheel.model) &&
                Objects.equals(color, wheel.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, sizeDiameter, sizeWidth, pcdCount, pcdDiameter, et, dia, color, factoryCode);
    }
}
