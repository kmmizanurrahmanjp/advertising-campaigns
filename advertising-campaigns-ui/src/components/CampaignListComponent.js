import React, { Component } from 'react'
import CampaignService from '../services/CampaignService'
import Popup from './Popup'
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";
//import CampaignPreviewComponent from './CampaignPreviewComponent'


class CampaignListComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            campaigns: [],
            previewCampaign: [],
            images: [],
            isOpen: false
        }
        this.addCampaign = this.addCampaign.bind(this);
        this.updateCampaign = this.updateCampaign.bind(this);
        this.closePopup = this.closePopup.bind(this);
    }

    
    componentDidMount(){
        CampaignService.getAllCampaigns().then((res) => {
            this.setState({ campaigns: res.data});
        });
    }
    
    previewCampaign(id){
        this.setState({ isOpen: !this.state.isOpen});

        CampaignService.getCampaignById(id).then((res) => {
            this.setState({ previewCampaign: res.data});
            const { banners } = this.state.previewCampaign;
            this.setState({ images: banners});
        });
    }

    closePopup(){
        this.setState({ isOpen: !this.state.isOpen});
    }

    updateCampaign(id){
        this.props.history.push(`/campaign/update/${id}`);
    }

    addCampaign(){
        this.props.history.push('/campaign/add');
    }


    render() {
        return (
            <div>
                 <h2 className="text-center">List of all Campaign</h2>
                 <br></br>
                 <div className = "row">
                    <button className="btn btn-primary" onClick={this.addCampaign}> Add Campaign</button>
                 </div>
                
                 <div className = "row">
                        <table className = "table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th> ID</th>
                                    <th> Campaign Name</th>
                                    <th> Campaign Start Date</th>
                                    <th> Campaign End Date</th>
                                    <th> Daily Budget</th>
                                    <th> Total Budget</th>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.campaigns.map(
                                        campaign => 
                                        <tr key = {campaign.id}>
                                             <td> {campaign.id} </td>
                                             <td> {campaign.name} </td>   
                                             <td> {campaign.dateFrom}</td>
                                             <td> {campaign.dateTo}</td>
                                             <td> {campaign.dailyBudget}</td>
                                             <td> {campaign.totalBudget}</td>
                                             <td>
                                                 <button onClick={ () => this.updateCampaign(campaign.id)} className="btn btn-info">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.previewCampaign(campaign.id)} className="btn btn-info">Preview </button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>
                 </div>


                 {/* <CampaignPreviewComponent campaignId={this.state.id} isOpen={this.state.isOpen}/> */}
                 <div className = "container">
                    {this.state.isOpen && <Popup
                        content={<>

                            <h4>{this.state.previewCampaign.name}</h4>
                            <p>Campaign ongoing from <b>{this.state.previewCampaign.dateFrom}</b> to <b>{this.state.previewCampaign.dateTo}</b></p>
                            
                            <div className="carousel-wrapper">
                            
                                <Carousel infiniteLoop autoPlay showIndicators>
                                    {
                                        this.state.images.map(
                                            image => 
                                            <div key = {image.bannerId}> 
                                                <img src={`data:image/jpg;base64,${image.banner}`}  alt={image.bannerId} ></img>
                                            </div>
                                                
                                        )
                                    }
                                </Carousel>
                            </div>
                        </>}
                        handleClose={this.closePopup}
                    />}
                </div>
            </div>
        )
    }
}

export default CampaignListComponent