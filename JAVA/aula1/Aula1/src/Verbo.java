package aula1.Aula1.src;

public class Verbo {
    private String radical;
    private String sufixo;

    public Verbo(String verbo){
        this.radical = verbo.substring(0, verbo.length()-2);
        this.sufixo = verbo.substring(verbo.length()-2);
    }

    public String getRadical(){
        return radical;
    }

    public void setRadical(String radical){
        this.radical = radical;
    }

    public String getSufixo(){
        return sufixo;
    }

    public void setSufixo(String sufixo){
        this.sufixo = sufixo;
    }

    public void conjugar(){
        System.out.println("Eu "+radical+"o");
        System.out.println("Tu "+radical+"as");
        System.out.println("Ele "+radical+"a");
        System.out.println("Nós "+radical+"amos");
        System.out.println("Vós "+radical+"ais");
        System.out.println("Eles "+radical+"am");
    }

    public String stringVerbo(){
        return radical+sufixo;
    }

}
