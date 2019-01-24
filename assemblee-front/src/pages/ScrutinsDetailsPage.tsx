import * as React from 'react';
import Api from 'src/api/api';
import VoteDetailsByGroup from 'src/model/VoteDetailsByGroup';
import VoteOverview from 'src/model/VoteOverview';
import { Link } from 'react-router-dom';

interface IScrutinsDetailsPageState {
    scrutinId: string,
    voteDetailsByGroup?: VoteDetailsByGroup[],
    voteOverview?: VoteOverview
}

export default class ScrutinsDetailsPage extends React.Component<{}, IScrutinsDetailsPageState> {

    constructor(props: any){
        super(props);

        // pathName == /scrutins/:id (see routing in App.tsx)
        // => parts: 0='' 1=scrutins 2=:id
        const pathNameParts: string[] = location.pathname.split('/');

        this.state = {
            scrutinId: pathNameParts[2]
        }
    }

    public componentDidMount() {

        Api.getVoteOverview(this.state.scrutinId)
        .then(response => {
            this.setState({
                voteOverview: response.data,
            });
        })
        .catch(error => {
            // TODO handle error
            alert(error);
        });
        
        Api.getVoteDetailsByGroup(this.state.scrutinId)
        .then(response => {
            this.setState({
                voteDetailsByGroup: response.data,
            });
        })
        .catch(error => {
            // TODO handle error
            alert(error);
        });
    }

    public render() {

        let rows;
        
        if(this.state.voteDetailsByGroup) {
            rows = this.state.voteDetailsByGroup.map((voteDetailsByGroup) => {
                return (
                    <tr key={voteDetailsByGroup.politicalBodyId}>
                        
                        <td>
                            <Link to={'/organes/'+voteDetailsByGroup.politicalBodyId}>
                                {voteDetailsByGroup.politicalBodyName}
                            </Link> 
                        </td>
                        
                        <td>{voteDetailsByGroup.choice}</td>
                        
                        <td>
                            Pour : {voteDetailsByGroup.numberFor}<br />
                            Contre : {voteDetailsByGroup.numberAgainst}<br />
                            Abstention : {voteDetailsByGroup.numberAbstention}<br />
                            Non-Votant : {voteDetailsByGroup.numberNoVote}
                        </td>
                    </tr>
                );
            });
        }

        let title = `Scrutin n°${this.state.scrutinId}`;
        let voteOverviewText;
        
        if(this.state.voteOverview) {
            title += ' : ' + this.state.voteOverview.title;

            voteOverviewText = (
                <div>
                    <p>Séance n°{this.state.voteOverview.meetingId} du {this.state.voteOverview.voteDate.toLocaleDateString('fr-FR')}</p>
                    <div>
                        Détails du vote : 
                        <ul>
                            <li>Pour : {this.state.voteOverview.numberFor}</li>
                            <li>Contre : {this.state.voteOverview.numberAgainst}</li>
                            <li>Abstention : {this.state.voteOverview.numberAbstention}</li>
                            <li>Non-Votant : {this.state.voteOverview.numberNoVote}</li> 
                        </ul>
                    </div>
                </div>);
        }

        return (
            <section className="section">
                <div className="container">

                    <h2 className="title">{title}</h2>

                    <div className="content">{voteOverviewText}</div>

                    <br />

                    <h3 className="subtitle">Répartition des votes par groupe politique :</h3>

                    <table className="table is-bordered is-narrow is-hoverable is-striped">

                        <thead>
                            <tr>
                                <th>Groupe à l'assemblée</th>
                                <th>Résultat</th>
                                <th>Détails</th>
                            </tr>
                        </thead>

                        <tbody>
                            {rows}
                        </tbody>
                    </table>
                </div>
            </section>
          );
    }
}