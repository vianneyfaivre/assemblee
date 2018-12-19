import * as React from 'react';
import Api from 'src/api/api';
import { PoliticalBodyMemberTable } from 'src/components/politicalBody/PoliticalBodyMemberTable';
import PoliticalBodyDetails from 'src/model/PoliticalBodyDetails';
import { DatePeriodLabel } from 'src/components/mandate/DatePeriodLabel';

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
        if(this.state.politicalBodyDetails) {
            
            if(this.state.politicalBodyDetails.legislature > 0) {
                title = this.state.politicalBodyDetails.politicalBodyLabel + ' (' + this.state.politicalBodyDetails.legislature + 'è législature)';
            } else {
                title = this.state.politicalBodyDetails.politicalBodyLabel;
            }
        }

        return (
            <section className="section">
                <div className="container">
                    <h2 className="title">Détail de l'organe {title}</h2>
                
                    {this.state.politicalBodyDetails && 
                        <div><DatePeriodLabel startDate={this.state.politicalBodyDetails.startDate} endDate={this.state.politicalBodyDetails.endDate} /></div> &&
                        <PoliticalBodyMemberTable members={this.state.politicalBodyDetails.members} />}
                </div>
            </section>
        );
    }
}