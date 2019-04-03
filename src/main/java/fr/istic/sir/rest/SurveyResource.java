package fr.istic.sir.rest;

import fr.istic.sir.entities.*;
import fr.istic.sir.entities.repository.SurveyRepository;
import fr.istic.sir.repositories.Repository;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Path("/surveys")
public class SurveyResource {
    private Repository<Survey> repository;

    public SurveyResource() {
        repository = new SurveyRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Survey> index() {
        return repository.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Survey store(JSONObject request) throws Exception {
        int surveyType = request.getInt("surveyType");

        Survey survey;
        JSONArray locations, dates;
        switch (surveyType) {
            case 0: // Date
                survey = new DateSurvey();
                ((DateSurvey) survey).setDates(associateDate(survey, request.getJSONArray("dates")));
                break;

            case 1: // Location
                survey = new AddressSurvey();
                ((AddressSurvey) survey).setAddresses(associateLocation(survey, request.getJSONArray("locations")));
                break;

            case 2: // Date and Location
                survey = new AddressAndDateSurvey();

                dates = request.getJSONArray("dates");
                ((AddressAndDateSurvey) survey).setDates(associateDate(survey, dates));

                locations = request.getJSONArray("locations");
                ((AddressAndDateSurvey) survey).setAddresses(associateLocation(survey, locations));
                break;

            default:
                return null;
        }
        repository.save(survey);

        return survey;
    }

    /**
     * @param survey    Survey to associate to
     * @param locations Locations to associate to
     * @throws JSONException JSONException
     */
    private List<Address> associateLocation(Survey survey, JSONArray locations) throws JSONException {
        List<Address> l = new ArrayList<>();
        for (int i = 0; i < locations.length(); ++i) {
            Address address = new Address(locations.getString(i));
            address.setSurvey(survey);
            l.add(address);
        }

        return l;
    }

    /**
     * @param survey Survey to associate to
     * @param dates  Locations to associate to
     * @throws JSONException  JSONException
     * @throws ParseException ParseException
     */
    private List<Date> associateDate(Survey survey, JSONArray dates) throws ParseException, JSONException {
        List<fr.istic.sir.entities.Date> l = new ArrayList<>();
        for (int i = 0; i < dates.length(); ++i) {
            fr.istic.sir.entities.Date d = new fr.istic.sir.entities.Date(parseToDate(dates.getString(i)));
            l.add(d);
            d.setSurvey(survey);
        }

        return l;
    }

    private java.util.Date parseToDate(String dateString) throws ParseException {
        SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.FRANCE);
        String date = dateString.replaceAll("\\+0([0-9]){1}\\:00", "+0$100");

        return iso8601Format.parse(date);
    }
}
