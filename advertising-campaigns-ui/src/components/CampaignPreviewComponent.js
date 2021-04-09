import React, { Component } from 'react'
import CampaignService from '../services/CampaignService';
import Popup from './Popup'
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";

class CampaignPreviewComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: '',
            previewCampaign: [],
            images: [],
            isOpen: ''
        }
        this.closePopup = this.closePopup.bind(this);
    }

    // step 3
    componentDidMount() {
        this.setState({ isOpen: true});
        const { id } = this.state;
        if(id != null || id !== undefined){
            CampaignService.getCampaignById(this.state.id).then((res) => {
                this.setState({ previewCampaign: res.data});
               const { banners } = this.state.previewCampaign;
               this.setState({ images: banners});
            });
        }
    }

    closePopup(){
        this.setState({ isOpen: !this.state.isOpen});
    }

    render() {
        return (
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
        )
    }
}

export default CampaignPreviewComponent