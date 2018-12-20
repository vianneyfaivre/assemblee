import * as React from 'react';
import Api from 'src/api/api';
import { Link, withRouter, RouteComponentProps } from 'react-router-dom';
import PoliticalBodySearchItem from 'src/model/PoliticalBodySearchItem';
import LabelService from 'src/util/LabelService';
/* tslint:disable-next-line:no-var-requires 
this module does not have the Typescript typings file so we must use "require" */
const Autocomplete = require("react-autocomplete") as any;

interface IPoliticalBodySearchPageState {
    politicalBodies: PoliticalBodySearchItem[],
    term: string
}

class PoliticalBodySearchPage extends React.Component<RouteComponentProps, IPoliticalBodySearchPageState> {

    constructor(props: any){
        super(props);
        this.state = { 
            politicalBodies: [],
            term: ''
        };
    }

    public render() {
        return (
            <section className="section">
                <div className="container">
                    <h2 className="title">Rechercher un organe</h2>

                    <div className="field">
                        <label htmlFor="political-body-search-input" className="label">Par nom : </label>
                        
                        <Autocomplete
                            wrapperProps={{className: "control"}}
                            wrapperStyle={{ position: 'relative', display: 'inline-block' }}
                            inputProps={{ name: 'political-body-search-input', className: "input" }}
                            value={this.state.term}
                            items={this.state.politicalBodies}
                            onChange={this.onInputChange}
                            onSelect={this.onItemSelect}
                            getItemValue={this.renderAutocompleteItemValue}
                            renderMenu={this.renderAutocompleteItems}
                            renderItem={this.renderAutocompleteItem}
                        />
                    </div>
                </div>
            </section>
        )
    }

    /**
     * Search for a political body when something gets typed in the search box
     */
    private onInputChange = (event: Event, value: string) => {
        this.setState({
            term: value
        });

        if(value.length > 1) {
            Api.searchPoliticalBodyByName(value)
                .then(response => {
                    this.setState({
                        politicalBodies: response.data,
                    });
                })
                .catch(error => {
                    // TODO handle error
                    alert(error);
                });
        } else {
            this.setState({
                politicalBodies: [],
            });
        }
    }

    /** 
     * Also redirect the user when he selects an item with the Enter key stroke 
     */
    private onItemSelect = (event: Event, value: PoliticalBodySearchItem) => {
        this.props.history.push('/organes/'+value.id);
    }

    /**
     * String value that will be passed as "value" parameter in method #onInputSelect
     */
    private renderAutocompleteItemValue(item: PoliticalBodySearchItem): string {
        return item.id;
    }

    /**
     * DOM Wrapper element for all autocomplete items
     */
    private renderAutocompleteItems(children: PoliticalBodySearchItem[]) {

        if(children.length === 0) {
            return <span />;
        }

        return (
            <div className="box">
                {children}
            </div>
        );
    }

    /**
     * Dom renderer for each autocomplete item
     */
    private renderAutocompleteItem(item: PoliticalBodySearchItem, isHighlighted: boolean) {
        return (
            <div
                className={`${isHighlighted ? 'has-background-light' : ''}`}
                key={item.id}
            >
                <Link to={'/organes/'+item.id}>
                    {LabelService.getItemTitle(item)}
                </Link> 
            </div>
        );
    }
}

export default withRouter(PoliticalBodySearchPage);