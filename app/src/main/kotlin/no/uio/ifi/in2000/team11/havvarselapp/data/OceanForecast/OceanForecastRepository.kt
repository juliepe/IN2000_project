package no.uio.ifi.in2000.team11.havvarselapp.data.OceanForecast

import no.uio.ifi.in2000.team11.havvarselapp.model.OceanForecast.OceanForecast
import no.uio.ifi.in2000.team11.havvarselapp.data.OceanForecast.OceanForecastDataSource



/**
 * mellomman mellom datasource og Viewmodel for Ocean Forecast-API'et
 * må sende inn latitude og longtitude for oprådet data skal hentes fra
 */
interface OceanForecastRepository{
    suspend fun getOceanForecast(lat: String, lon: String): OceanForecast?
}

class OceanForecastRepositoryImpl(
    private val dataSource: OceanForecastDataSource = OceanForecastDataSource()
): OceanForecastRepository {

    override suspend fun getOceanForecast(lat: String, lon: String): OceanForecast {
        return dataSource.fetchOceanForecast(lat, lon)
    }
}