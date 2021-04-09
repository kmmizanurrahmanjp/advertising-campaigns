import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import CampaignListComponent from './components/CampaignListComponent';
import HeaderComponent from './components/HeaderComponent';
//import FooterComponent from './components/FooterComponent';
import CreateCampaignComponent from './components/CreateCampaignComponent';
import CampaignPreviewComponent from './components/CampaignPreviewComponent';


function App() {
  return (
    <div>
       <HeaderComponent /> 
        <Router>
                <div className="container">
                    <Switch> 
                          <Route path = "/" exact component = {CampaignListComponent}></Route>
                          <Route path = "/campaigns" component = {CampaignListComponent}></Route>
                          <Route path = "/campaign/add" component = {CreateCampaignComponent}></Route>
                          <Route path = "/campaign/update/:id" component = {CreateCampaignComponent}></Route>
                          <Route path = "/campaign/preview/:id" component = {CampaignPreviewComponent}></Route>
                    </Switch>
                </div>
        </Router>
        {/* <FooterComponent /> */}
    </div>
    
  );
}

export default App;
