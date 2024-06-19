package model;

public class Credenziali {

        private final String username;
        private final String password;
        private final Ruolo ruolo;
        private final String matricola;

        public Credenziali(String username, String password, Ruolo ruolo, String matricola) {
            this.username = username;
            this.password = password;
            this.ruolo = ruolo;
            this.matricola = matricola;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public Ruolo getRuolo() {
            return ruolo;
        }

        public String getMatricola() { return matricola; }
}
