package ru.mail.romanov1234567890987.repository.model;

public class Car {
    private Integer id;
    private String name;
    private CarModelsNamesEnum carModelsNamesEnum;
    private Integer engineCapacity;

    private Car(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setCarModelsNamesEnum(builder.carModelsNamesEnum);
        setEngineCapacity(builder.engineCapacity);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return getName() + " " + carModelsNamesEnum.getName() +
                " " + getEngineCapacity() + "\n";
    }


    public String getName() {
        return name;
    }

    public String getCarModelsNamesEnum() {
        return carModelsNamesEnum.getName();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCarModelsNamesEnum(CarModelsNamesEnum carModelsNamesEnum) {
        this.carModelsNamesEnum = carModelsNamesEnum;
    }

    public void setEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public static final class Builder {
        private Integer id;
        private String name;
        private CarModelsNamesEnum carModelsNamesEnum;
        private Integer engineCapacity;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder carModelsNamesEnum(CarModelsNamesEnum val) {
            carModelsNamesEnum = val;
            return this;
        }

        public Builder engineCapacity(Integer val) {
            engineCapacity = val;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
