import axios from 'axios';

const CAMPAIGN_API_BASE_URL = "http://localhost:8086/api/campaign";

class CampaignService {

    getAllCampaigns(){
        return axios.get(CAMPAIGN_API_BASE_URL);
    }

    getCampaignById(id){
        return axios.get(CAMPAIGN_API_BASE_URL + '/' + id);
    }

    createOrUpdateCampaign(campaign){
        return axios.post(CAMPAIGN_API_BASE_URL, campaign);
    }
}

export default new CampaignService()