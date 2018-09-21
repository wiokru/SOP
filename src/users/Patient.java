package users;

public class Patient extends User {

    private String address;
    private String phone;

    private Patient(){}

    public static Builder builder(){
        return new Patient.Builder();
    }

    public static class Builder {

        private Patient instance = new Patient();

        private Builder(){}

        public Builder id (String id){
            instance.setId(id);
            return this;
        }

        public Builder name (String name){
            instance.setName(name);
            return this;
        }

        public Builder surname (String surname){
            instance.setSurname(surname);
            return this;
        }

        public Builder email(String email){
            instance.setEmail(email);
            return this;
        }

        public Builder password (char [] password){
            instance.setPassword(password);
            return this;
        }

        public Builder address (String address){
            instance.address = address;
            return this;
        }

        public Builder phone (String phone){
            instance.phone = phone;
            return this;
        }

        public Patient build(){
            assert instance.getId() != null : "id is null!!";
            return instance;
        }
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return super.toString() + ", tel: " + phone ;
    }

}


