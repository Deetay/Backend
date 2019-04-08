package rideshare.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rideshare.demo.Entity.City;
import rideshare.demo.Repository.CityRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> list() {
        return cityRepository.findAllByOrderByCityId();
    }

    public void saveAll(List<City> cities) {
        cityRepository.saveAll(cities);
    }


    public void removeAll() {
        cityRepository.deleteAll();
    }

    public Optional<City> get(Long cityId) { return cityRepository.findById(cityId);
    }

    public List<City> search(String name, int limit) {

        List<City> result = cityRepository.findCities(name, PageRequest.of(0, limit));
        return result;
    }
    @PostConstruct
    public List<City> generate() {

        List<List<Object>> v =  Arrays.asList(
                Arrays.asList("Warszawa", "powiat Warszawa", "mazowieckie", (long) 1724404),
                Arrays.asList("Kraków", "powiat Kraków", "małopolskie", (long) 758992),
                Arrays.asList("Łódź", "powiat Łódź", "łódzkie", (long) 711332),
                Arrays.asList("Wrocław", "powiat Wrocław", "dolnośląskie", (long) 632067),
                Arrays.asList("Poznań", "powiat Poznań", "wielkopolskie", (long) 548028),
                Arrays.asList("Gdańsk", "powiat Gdańsk", "pomorskie", (long) 461531),
                Arrays.asList("Szczecin", "powiat Szczecin", "zachodniopomorskie", (long) 408172),
                Arrays.asList("Bydgoszcz", "powiat Bydgoszcz", "kujawsko-pomorskie", (long) 359428),
                Arrays.asList("Lublin", "powiat Lublin", "lubelskie", (long) 343598),
                Arrays.asList("Katowice", "powiat Katowice", "śląskie", (long) 304362),
                Arrays.asList("Białystok", "powiat Białystok", "podlaskie", (long) 295282),
                Arrays.asList("Gdynia", "powiat Gdynia", "pomorskie", (long) 248042),
                Arrays.asList("Częstochowa", "powiat Częstochowa", "śląskie", (long) 232318),
                Arrays.asList("Radom", "powiat Radom", "mazowieckie", (long) 218466),
                Arrays.asList("Sosnowiec", "powiat Sosnowiec", "śląskie", (long) 211275),
                Arrays.asList("Toruń", "powiat Toruń", "kujawsko-pomorskie", (long) 203447),
                Arrays.asList("Kielce", "powiat Kielce", "świętokrzyskie", (long) 199870),
                Arrays.asList("Gliwice", "powiat Gliwice", "śląskie", (long) 185450),
                Arrays.asList("Rzeszów", "powiat Rzeszów", "podkarpackie", (long) 183108),
                Arrays.asList("Zabrze", "powiat Zabrze", "śląskie", (long) 178357),
                Arrays.asList("Olsztyn", "powiat Olsztyn", "warmińsko-mazurskie", (long) 174675),
                Arrays.asList("Bielsko-Biała", "powiat Bielsko-Biała", "śląskie", (long) 173699),
                Arrays.asList("Bytom", "powiat Bytom", "śląskie", (long) 173439),
                Arrays.asList("Ruda Śląska", "powiat Ruda Śląska", "śląskie", (long) 141521),
                Arrays.asList("Rybnik", "powiat Rybnik", "śląskie", (long) 140173),
                Arrays.asList("Tychy", "powiat Tychy", "śląskie", (long) 128799),
                Arrays.asList("Gorzów Wielkopolski", "powiat Gorzów Wielkopolski", "lubuskie", (long) 124344),
                Arrays.asList("Dąbrowa Górnicza", "powiat Dąbrowa Górnicza", "śląskie", (long) 123994),
                Arrays.asList("Elbląg", "powiat Elbląg", "warmińsko-mazurskie", (long) 122899),
                Arrays.asList("Płock", "powiat Płock", "mazowieckie", (long) 122815),
                Arrays.asList("Opole", "powiat Opole", "opolskie", (long) 120146),
                Arrays.asList("Zielona Góra", "powiat Zielona Góra", "lubuskie", (long) 118405),
                Arrays.asList("Wałbrzych", "powiat Wałbrzych", "dolnośląskie", (long) 117926),
                Arrays.asList("Włocławek", "powiat Włocławek", "kujawsko-pomorskie", (long) 114885),
                Arrays.asList("Tarnów", "powiat Tarnów", "małopolskie", (long) 112120),
                Arrays.asList("Chorzów", "powiat Chorzów", "śląskie", (long) 110761),
                Arrays.asList("Koszalin", "powiat Koszalin", "zachodniopomorskie", (long) 109170),
                Arrays.asList("Kalisz", "powiat Kalisz", "wielkopolskie", (long) 103997),
                Arrays.asList("Legnica", "powiat Legnica", "dolnośląskie", (long) 101992),
                Arrays.asList("Grudziądz", "powiat Grudziądz", "kujawsko-pomorskie", (long) 97676),
                Arrays.asList("Słupsk", "powiat Słupsk", "pomorskie", (long) 93936),
                Arrays.asList("Jaworzno", "powiat Jaworzno", "śląskie", (long) 93708),
                Arrays.asList("Jastrzębie-Zdrój", "powiat Jastrzębie-Zdrój", "śląskie", (long) 91235),
                Arrays.asList("Nowy Sącz", "powiat Nowy Sącz", "małopolskie", (long) 83943),
                Arrays.asList("Jelenia Góra", "powiat Jelenia Góra", "dolnośląskie", (long) 81985),
                Arrays.asList("Konin", "powiat Konin", "wielkopolskie", (long) 77224),
                Arrays.asList("Siedlce", "powiat Siedlce", "mazowieckie", (long) 76347),
                Arrays.asList("Piotrków Trybunalski", "powiat Piotrków Trybunalski", "łódzkie", (long) 75903),
                Arrays.asList("Mysłowice", "powiat Mysłowice", "śląskie", (long) 75129),
                Arrays.asList("Inowrocław", "powiat inowrocławski", "kujawsko-pomorskie", (long) 75001),
                Arrays.asList("Piła", "powiat pilski", "wielkopolskie", (long) 74609),
                Arrays.asList("Lubin", "powiat lubiński", "dolnośląskie", (long) 74053),
                Arrays.asList("Ostrów Wielkopolski", "powiat ostrowski", "wielkopolskie", (long) 72890),
                Arrays.asList("Ostrowiec Świętokrzyski", "powiat ostrowiecki", "świętokrzyskie", (long) 72277),
                Arrays.asList("Gniezno", "powiat gnieźnieński", "wielkopolskie", (long) 69883),
                Arrays.asList("Stargard", "powiat stargardzki", "zachodniopomorskie", (long) 69328),
                Arrays.asList("Suwałki", "powiat Suwałki", "podlaskie", (long) 69317),
                Arrays.asList("Głogów", "powiat głogowski", "dolnośląskie", (long) 68997),
                Arrays.asList("Siemianowice Śląskie", "powiat Siemianowice Śląskie", "śląskie", (long) 68844),
                Arrays.asList("Pabianice", "powiat pabianicki", "łódzkie", (long) 67688),
                Arrays.asList("Chełm", "powiat Chełm", "lubelskie", (long) 65481),
                Arrays.asList("Zamość", "powiat Zamość", "lubelskie", (long) 65255),
                Arrays.asList("Tomaszów Mazowiecki", "powiat tomaszowski", "łódzkie", (long) 64893),
                Arrays.asList("Leszno", "powiat Leszno", "wielkopolskie", (long) 64589),
                Arrays.asList("Stalowa Wola", "powiat stalowowolski", "podkarpackie", (long) 63692),
                Arrays.asList("Przemyśl", "powiat Przemyśl", "podkarpackie", (long) 63638),
                Arrays.asList("Kędzierzyn-Koźle", "powiat kędzierzyńsko-kozielski", "opolskie", (long) 63194),
                Arrays.asList("Łomża", "powiat Łomża", "podlaskie", (long) 62711),
                Arrays.asList("Żory", "powiat Żory", "śląskie", (long) 62038),
                Arrays.asList("Mielec", "powiat mielecki", "podkarpackie", (long) 61096),
                Arrays.asList("Tarnowskie Góry", "powiat tarnogórski", "śląskie", (long) 60957),
                Arrays.asList("Tczew", "powiat tczewski", "pomorskie", (long) 60610),
                Arrays.asList("Ełk", "powiat ełcki", "warmińsko-mazurskie", (long) 59790),
                Arrays.asList("Pruszków", "powiat pruszkowski", "mazowieckie", (long) 59570),
                Arrays.asList("Bełchatów", "powiat bełchatowski", "łódzkie", (long) 59565),
                Arrays.asList("Świdnica", "powiat świdnicki", "dolnośląskie", (long) 59182),
                Arrays.asList("Będzin", "powiat będziński", "śląskie", (long) 58425),
                Arrays.asList("Biała Podlaska", "powiat Biała Podlaska", "lubelskie", (long) 57658),
                Arrays.asList("Zgierz", "powiat zgierski", "łódzkie", (long) 57503),
                Arrays.asList("Piekary Śląskie", "powiat Piekary Śląskie", "śląskie", (long) 57148),
                Arrays.asList("Racibórz", "powiat raciborski", "śląskie", (long) 55930),
                Arrays.asList("Legionowo", "powiat legionowski", "mazowieckie", (long) 54231),
                Arrays.asList("Ostrołęka", "powiat Ostrołęka", "mazowieckie", (long) 52917),
                Arrays.asList("Świętochłowice", "powiat Świętochłowice", "śląskie", (long) 51824),
                Arrays.asList("Zawiercie", "powiat zawierciański", "śląskie", (long) 51258),
                Arrays.asList("Starachowice", "powiat starachowicki", "świętokrzyskie", (long) 51158),
                Arrays.asList("Wejherowo", "powiat wejherowski", "pomorskie", (long) 50340),
                Arrays.asList("Puławy", "powiat puławski", "lubelskie", (long) 49100, (long) 50),
                Arrays.asList("Wodzisław Śląski", "powiat wodzisławski", "śląskie", (long) 48731),
                Arrays.asList("Skierniewice", "powiat Skierniewice", "łódzkie", (long) 48634),
                Arrays.asList("Starogard Gdański", "powiat starogardzki", "pomorskie", (long) 48621),
                Arrays.asList("Tarnobrzeg", "powiat Tarnobrzeg", "podkarpackie", (long) 48217),
                Arrays.asList("Radomsko", "powiat radomszczański", "łódzkie", (long) 47643),
                Arrays.asList("Skarżysko-Kamienna", "powiat skarżyski", "świętokrzyskie", (long) 47538),
                Arrays.asList("Rumia", "powiat wejherowski", "pomorskie", (long) 47374),
                Arrays.asList("Krosno", "powiat Krosno", "podkarpackie", (long) 47223),
                Arrays.asList("Kołobrzeg", "powiat kołobrzeski", "zachodniopomorskie", (long) 46897),
                Arrays.asList("Dębica", "powiat dębicki", "podkarpackie", (long) 46854),
                Arrays.asList("Kutno", "powiat kutnowski", "łódzkie", (long) 45657),
                Arrays.asList("Otwock", "powiat otwocki", "mazowieckie", (long) 45044)
                );

        List<City> cities = new ArrayList<>();
        for (int i = 0; i < v.size() ; i++) {
            List<Object> x = v.get(i);
            City c = new City();
            c.setCityId((long) i+1);
            c.setName((String) x.get(0));
            c.setCounty((String) x.get(1));
            c.setProvince((String) x.get(2));
            c.setPopulation((Long) x.get(3));
            cities.add(c);
        }
        removeAll();
        saveAll(cities);
        return cities;
    }


}
