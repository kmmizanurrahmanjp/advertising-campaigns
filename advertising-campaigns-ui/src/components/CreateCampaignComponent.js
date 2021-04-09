import React, { Component } from 'react'
import CampaignService from '../services/CampaignService';

class CreateCampaignComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            name: '',
            dateFrom: '',
            dateTo: '',
            dailyBudget: '',
            totalBudget: '',
            banners: []
        }
        this.saveOrUpdateCampaign = this.saveOrUpdateCampaign.bind(this);
    }

    
    componentDidMount(){
        const { id } = this.state;
        if(id != null || id !== undefined){
            CampaignService.getCampaignById(this.state.id).then( (res) =>{
                let campaign = res.data;
                this.setState({
                    name: campaign.name,
                    dateFrom: campaign.dateFrom,
                    dateTo : campaign.dateTo,
                    dailyBudget : campaign.dailyBudget,
                    totalBudget : campaign.totalBudget,
                    banners: campaign.banners
                });
            });
        }
    }
    saveOrUpdateCampaign = (e) => {
        e.preventDefault();
        let campaign = {
            id: this.state.id, 
            name: this.state.name, 
            dateFrom: this.state.dateFrom, 
            dateTo: this.state.dateTo, 
            dailyBudget: this.state.dailyBudget, 
            totalBudget: this.state.totalBudget
        };
        
        const formData = new FormData();
        formData.append('campaign', new Blob([JSON.stringify(campaign)], {
            type: "application/json"
        }));

        const { banners } = this.state;
        for(let i = 0; i< banners.length; i++) {
            formData.append('files', banners[i])
        }

        CampaignService.createOrUpdateCampaign(formData).then( res => {
            this.props.history.push('/campaigns');
        });
    }
    
    changeNameHandler= (event) => {
        this.setState({name: event.target.value});
    }

    changeDateFromHandler= (event) => {
        this.setState({dateFrom: event.target.value});
    }

    changeDateToHandler= (event) => {
        this.setState({dateTo: event.target.value});
    }

    changeDailyBudgetHandler= (event) => {
        this.setState({dailyBudget: event.target.value});
    }

    changeTotalBudgetHandler= (event) => {
        this.setState({totalBudget: event.target.value});
    }

    changeBannerHandler= (event) => {
        var files = [];
        for(let i = 0; i< event.target.files.length; i++) {
            files.push(event.target.files[i]);
        }
        this.setState({banners: files});
    }

    cancel(){
        this.props.history.push('/campaigns');
    }

    getTitle(){
        const { id } = this.state;
        if(id == null || id === undefined){
            return <h3 className="text-center">Add Campaign</h3>
        }else{
            return <h3 className="text-center">Update Campaign</h3>
        }
    }



    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Name: </label>
                                            <input type="text" placeholder="Name" name="name" className="form-control" 
                                                 value={this.state.name} onChange={this.changeNameHandler}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> Start Date: </label>
                                            <input type="date" placeholder="Start Date" name="dateFrom" className="form-control" 
                                                value={this.state.dateFrom} onChange={this.changeDateFromHandler}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> End Date: </label>
                                            <input type="date" placeholder="End Date" name="dateTo" className="form-control" 
                                                value={this.state.dateTo} onChange={this.changeDateToHandler}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> Daily Budget: </label>
                                            <input type="number" placeholder="Daily Budget" name="dailyBudget" className="form-control" 
                                                value={this.state.dailyBudget} onChange={this.changeDailyBudgetHandler}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> Total Budget : </label>
                                            <input type="number" placeholder="Total Budget" name="totalBudget" className="form-control" 
                                                value={this.state.totalBudget} onChange={this.changeTotalBudgetHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Upload Banners : </label>
                                            <input type="file" multiple name="banners" className="form-control" 
                                                onChange={this.changeBannerHandler}/>
                                        </div>
                                        <button className="btn btn-success" onClick={this.saveOrUpdateCampaign}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                   </div>
            </div>
        )
    }
}

export default CreateCampaignComponent