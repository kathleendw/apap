package apap.tutorial.celciusConverter.model;

public class CelciusConverter {
    private Double celcius;

    public CelciusConverter(Double celcius){
        this.celcius = celcius;
    }

    public String ConvertToFahrenheit(){
        double fahrenheit = (celcius * 9 / 5) + 32;
        return String.format("%.2f", fahrenheit);
    }

    public String ConvertToKelvin(){
        double kelvin = celcius + 273.15;
        return String.format("%.2f", kelvin);
    }

    public String ConvertToRankine(){
        double rankine = (celcius + 273.15) * 9 / 5;
        return String.format("%.2f", rankine);
    }

    public double getCelcius(){
        return celcius;
    }
}
