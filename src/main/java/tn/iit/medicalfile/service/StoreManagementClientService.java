package tn.iit.medicalfile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import tn.iit.medicalfile.config.Links;
import tn.iit.medicalfile.dto.MedicineDto;

import java.util.List;

@Service
public class StoreManagementClientService {
    private Logger logger = LoggerFactory.getLogger (StoreManagementClientService.class);
    private final RestTemplate restTemplate;
    public StoreManagementClientService(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }
    public List<MedicineDto> getAllMedicines(){
        logger.debug("Getting all medicines");
        return restTemplate.exchange (Links.MEDICINES ,
                HttpMethod.GET,
                new HttpEntity<> (null,new HttpHeaders ()),
                new ParameterizedTypeReference<List<MedicineDto>> (){}).getBody ();
    }
    public MedicineDto getMedicineById(Long id){
        logger.debug ("Getting medicine with id {}",id);

        return restTemplate.exchange (Links.MEDICINES+"/" +id,
                HttpMethod.GET,
                new HttpEntity<> (null,new HttpHeaders ()),
                MedicineDto.class).getBody ();
    }
    public List<MedicineDto> getMedicinesByIds(List<Long> ids){
        this.logger.debug ("Getting medicines with ids {}",ids);
        return restTemplate.exchange (Links.MEDICINES+"/search",
                HttpMethod.POST,
                new HttpEntity<> (ids,new HttpHeaders ()),
                new ParameterizedTypeReference<List<MedicineDto>> (){}).getBody ();
    }

}
