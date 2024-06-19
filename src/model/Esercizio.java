package model;

public class Esercizio {

    private Integer index;
    private String nome;
    private Macchinario macchinario;
    private Integer serie;
    private Integer ripetizioni;

    public Esercizio(String nome, String macchinario) {
        this.nome = nome;
        if (macchinario == null) {
            this.macchinario = new Macchinario("Nessuno", null);
            return;
        }
        this.macchinario = new Macchinario(macchinario, null);
    }
    public Esercizio(String nome, Integer serie, Integer ripetizioni) {
        this.nome = nome;
        this.serie = serie;
        this.ripetizioni = ripetizioni;
    }
    public Esercizio(Integer Index, String nome, Integer serie, Integer ripetizioni, String macchinario) {
        this.index = Index;
        this.nome = nome;
        this.serie = serie;
        this.ripetizioni = ripetizioni;
        if (macchinario == null) {
            this.macchinario = new Macchinario("Nessuno", null);
            return;
        }
        this.macchinario = new Macchinario(macchinario, null);
    }

    public Integer getSerie() {
        return serie;
    }
    public Integer getRipetizioni() {
        return ripetizioni;
    }

    public String getNome() {
        return nome;
    }

    public Macchinario getMacchinario() {
        return macchinario;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.index).append(" - ").append(this.nome).append(" | Serie: ").append(this.serie).append(" | Ripetizioni: ").append(this.ripetizioni).append(" | Macchinario: ").append(this.macchinario.getNome());
        return sb.toString();
    }

    public String toString_short(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.nome).append(" | Macchinario: ").append((this.macchinario.getNome()));
        return sb.toString();
    }


}
