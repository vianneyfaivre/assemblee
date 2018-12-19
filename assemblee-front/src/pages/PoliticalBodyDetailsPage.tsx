import * as React from 'react';
import Api from 'src/api/api';
import { PoliticalBodyMemberTable } from 'src/components/politicalBody/PoliticalBodyMemberTable';
import PoliticalBodyDetails from 'src/model/PoliticalBodyDetails';
import { PoliticalBodyDetailsPanel } from 'src/components/politicalBody/PoliticalBodyDetailsPanel';
import LabelService from 'src/util/LabelService';

interface IPoliticalBodyDetailsPageState {
    organeId: string,
    politicalBodyDetails?: PoliticalBodyDetails
}

export default class PoliticalBodyDetailsPage extends React.Component<{}, IPoliticalBodyDetailsPageState> {

    constructor(props: any){
        super(props);

        // pathName == /organes/:organeId (see routing in App.tsx)
        // => parts: 0='' 1=organes 2=:organeId
        const pathNameParts: string[] = location.pathname.split('/');

        this.state = {
            organeId: pathNameParts[2],
        }
    }

    public componentDidMount() {
        Api.getPoliticalBodyMembers(this.state.organeId)
            .then(response => {
                this.setState({
                    politicalBodyDetails: response.data,
                });
            })
            .catch(error => {
                // TODO handle error
                alert(error);
            });
    }

    public render() {

        let title = '';
        let body = <div />;
        
        if(this.state.politicalBodyDetails) {

            title = LabelService.getPageTitle(this.state.politicalBodyDetails);

            body = (
                <div>
                    <PoliticalBodyDetailsPanel details={this.state.politicalBodyDetails} />
                    
                    <div style={{marginTop: '50px'}}>
                        <PoliticalBodyMemberTable members={this.state.politicalBodyDetails.members} />
                    </div>
                </div>
            );
        }

        return (
            <section className="section">
                <div className="container">
                    <h2 className="title">{title}</h2>
                
                    {this.state.politicalBodyDetails && body}
                </div>
            </section>
        );
    }
}