package src.main.java;
public class Cliente {
    private String cpf;
    private String nome;
    private String telefone;
    private String escola;
    private double valor;

    public Cliente(String cpf, String nome, String telefone, String escola, double valor) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.escola = escola;
        this.valor = valor;
    }

    public Cliente(){

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    public static boolean isValidCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula os dígitos verificadores
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(cpf.charAt(i));
        }

        // Calcula o primeiro dígito verificador
        int sum1 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += digits[i] * (10 - i);
        }
        int check1 = 11 - (sum1 % 11);
        if (check1 >= 10) {
            check1 = 0;
        }

        // Calcula o segundo dígito verificador
        int sum2 = 0;
        for (int i = 0; i < 10; i++) {
            sum2 += digits[i] * (11 - i);
        }
        int check2 = 11 - (sum2 % 11);
        if (check2 >= 10) {
            check2 = 0;
        }

        // Verifica se os dígitos verificadores estão corretos
        return check1 == digits[9] && check2 == digits[10];
    }
}
