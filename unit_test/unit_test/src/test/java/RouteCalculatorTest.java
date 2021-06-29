import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase {

    List<Station> route;

    List<Station> noConnections;
    List<Station> oneConnections;
    List<Station> twoConnections;

    StationIndex stationIndex;
    RouteCalculator calculator;

    Station from;
    Station to;
    Station zvenigorodskaya;
    Station pushkinskaya;
    Station maykovskay;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        route = new ArrayList<>();
        stationIndex =  new StationIndex();

        Line line5 = new Line(5, "Фрунзенско-Приморская");
        Line line3 = new Line(3, "Невско-Василеостровская");
        Line line1 = new Line(1, "Кировско-Выборгская");

        from = new Station("Адмиралтейская", line5);
        Station sadovaya = new Station("Садовая", line5);
        zvenigorodskaya = new Station("Звенигородская", line5);

        pushkinskaya = new Station("Пушкинская", line1);
        Station vladimirskaya = new Station("Владимирская", line1);
        Station ploshadyVosstanya = new Station("Площадь Восстания", line1);

        maykovskay = new Station("Маяковская", line3);
        Station ploshadyAleksandraNevskogo = new Station("площадь Александра Невского", line3);
        to = new Station("Елизаровская", line3);

        route.addAll(Stream.of(from, sadovaya, zvenigorodskaya, pushkinskaya, vladimirskaya, ploshadyVosstanya).collect(Collectors.toList()));

        Stream.of(line1, line3, line5).forEach(stationIndex::addLine);

        Stream.of(from, sadovaya, zvenigorodskaya, pushkinskaya, vladimirskaya, ploshadyVosstanya, maykovskay, ploshadyAleksandraNevskogo, to).peek(stations -> stations.getLine().addStation(stations)).forEach(stationIndex::addStation);

        stationIndex.addConnection(Stream.of(zvenigorodskaya, pushkinskaya).collect(Collectors.toList()));
        stationIndex.addConnection(Stream.of(ploshadyVosstanya, maykovskay).collect(Collectors.toList()));

        stationIndex.getConnectedStations(zvenigorodskaya);
        stationIndex.getConnectedStations(ploshadyVosstanya);


        calculator = new RouteCalculator(stationIndex);

        noConnections = Stream.of(from, sadovaya, zvenigorodskaya).collect(Collectors.toList());
        oneConnections = Stream.of(from, sadovaya, zvenigorodskaya, pushkinskaya).collect(Collectors.toList());
        twoConnections = Stream.of(from, sadovaya, zvenigorodskaya, pushkinskaya, vladimirskaya, ploshadyVosstanya, maykovskay, ploshadyAleksandraNevskogo, to).collect(Collectors.toList());

    }

    public void testCalculateDuration()
    {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 13.5;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute ()
    {
        List<Station> actualNoConnections = calculator.getShortestRoute(from, zvenigorodskaya);
        List<Station> actualOneConnections = calculator.getShortestRoute(from, pushkinskaya);
        List<Station> actualTwoConnections = calculator.getShortestRoute(from, to);

        List<Station> expectedNoConnections = noConnections;
        List<Station> expectedOneConnections = oneConnections;
        List<Station> expectedTwoConnections = twoConnections;


        assertEquals(expectedNoConnections, actualNoConnections);
        assertEquals(expectedOneConnections, actualOneConnections);
        assertEquals(expectedTwoConnections, actualTwoConnections);
    }
}
