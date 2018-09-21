package users;

public class Doctor extends User {

    private String specialization;

    private Doctor(){}

    public static Builder builder(){
        return new Doctor.Builder();
    }

    public static class Builder {

        private Doctor instance = new Doctor();

        private Builder() {}

        public Builder id(String id) {
            instance.setId(id);
            return this;
        }

        public Builder name(String name) {
            instance.setName(name);
            return this;
        }

        public Builder surname(String surname) {
            instance.setSurname(surname);
            return this;
        }

        public Builder email(String email){
            instance.setEmail(email);
            return this;
        }

        public Builder password(char[] password) {
            instance.setPassword(password);
            return this;
        }

        public Builder specialization(String specialization){
            instance.specialization = specialization;
            return this;
        }

        public Doctor build(){
            assert instance.getId() != null : "id is null!!";
            return instance;
        }
    }

    @Override
    public String toString() {
        return "dr " + super.toString() + ", " + specialization;
    }
}
