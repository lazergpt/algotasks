package by.algotsk;

// Пример реализации глубокого копирования через конструктор копирования и clone

//Также В Apache Commons Lang есть SerializationUtils#clone, который выполняет глубокое копирование, если все классы в графе объектов реализуют интерфейс Serializable.
//Если метод встретит класс, который не является сериализуемым, он выдаст ошибку и сгенерирует непроверяемое исключение SerializationException.
class ObjectToBeCopiedFirs implements Cloneable // если не заимплементить маркерный интерфейс, то CloneNotSupportedException будет брошен.
{
    private String address1;
    private  int zipCode;
    private InternalFirst buildingName;

    // 1. Через конструктор копирования
    public ObjectToBeCopiedFirs(ObjectToBeCopiedFirs that)
    {
        address1 = that.getAddress1();
        zipCode = that.getZipCode();
        buildingName = new InternalFirst(that.getBuildingName());
    }

    public String getAddress1() {
        return address1;
    }

    public int getZipCode() {
        return zipCode;
    }

    public InternalFirst getBuildingName() {
        return buildingName;
    }

    // 2. Через clone
    @Override
    public Object clone() {
        ObjectToBeCopiedFirs otbc = null;

        try {
            otbc = (ObjectToBeCopiedFirs) super.clone();
        } catch (CloneNotSupportedException e) {
            otbc = new ObjectToBeCopiedFirs(this);
        }

        otbc.buildingName = (InternalFirst) this.getBuildingName().clone();
        return otbc;

    }

    class InternalFirst{
        private String buildingName;

        public InternalFirst(InternalFirst that) {
            buildingName = that.getBuildingName();
        }

        public String getBuildingName() {
            return buildingName;
        }

        @Override
        public Object clone()
        {
            try {
                return (InternalFirst) super.clone();
            } catch (CloneNotSupportedException e) {
                return new InternalFirst(this);
            }
        }
    }

}
