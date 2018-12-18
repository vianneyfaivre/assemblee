import * as React from 'react';
import Api from 'src/api/api';
import PoliticalBodyMember from 'src/model/PoliticalBodyMember';
import { PoliticalBodyMemberTable } from 'src/components/politicalBody/PoliticalBodyMemberTable';

interface IPoliticalBodyDetailsPageState {
    organeId: string,
    members: PoliticalBodyMember[]
}

export default class PoliticalBodyDetailsPage extends React.Component<{}, IPoliticalBodyDetailsPageState> {

    constructor(props: any){
        super(props);

        // pathName == /organes/:organeId (see routing in App.tsx)
        // => parts: 0='' 1=organes 2=:organeId
        const pathNameParts: string[] = location.pathname.split('/');

        this.state = {
            organeId: pathNameParts[2],
            members: []
        }
    }

    public componentDidMount() {
        Api.getPoliticalBodyMembers(this.state.organeId)
            .then(response => {
                this.setState({
                    members: response.data,
                });
            })
            .catch(error => {
                // TODO handle error
                alert(error);
            });
    }

    public render() {
        return (
            <section className="section">
                <div className="container">
                    <h2 className="title">Détail de l'organe politique "TODO"</h2>
                
                    <PoliticalBodyMemberTable members={this.state.members} />
                </div>
            </section>
        );
    }
}