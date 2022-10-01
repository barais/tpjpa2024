import React from "react"
import constantes from "../constantes.json"
import axios from "axios"


export default class LogInDoctor extends React.Component {
    
    constructor(props) {
        super(props)
        this.state = {
            doctors: [],
            doctorChoice: "",
            newDoctor: {
                firstName: "",
                lastName: "",
                specialisation: ""
            },
            specialities: []
        }

        this.handleInputChange = this.handleInputChange.bind(this);
        this.onChange = this.onChange.bind(this);
        this.sendForm = this.sendForm.bind(this);

    }


    componentDidMount() {

        axios.get(constantes.url_back + "/doctors")
            .then((res) => {
                const resData = res.data
                this.setState(prevState => ({ ...prevState, doctors: resData }))
            })


           axios.get(constantes.url_back + "/specialisation")
           .then((res) => {
               console.log((res.data))
               const resData = res.data
               this.setState(prevState => ({ ...prevState, specialities: resData }))
           })
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
        this.setState((oldState) => ({
            ...oldState,
            newDoctor: {
                ...oldState.newDoctor, [name]: value
            }
        }));
    }


    onChange(event) {

        const name = event.target.name
        const value = event.target.value

        this.setState(({
            [name]: value
        }))
    }

    sendForm(event) {
        event.preventDefault();
        axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
        axios.post(constantes.url_back + "/doctors", this.state.newDoctor)
            .then(response => {
                this.setState(prevState => ({
                    doctors: [...prevState.doctors, response.data],
                    newDoctor: {
                        firstName: "",
                        lastName: ""
                    },
                    doctorChoice: response.data.id
                }))
            })

    }

    render() {
        return (
            <div>
                <label>
                    Choisissez votre profile
                    <select value={this.state.doctorChoice} name="doctorChoice" onChange={this.onChange}>
                        {this.state.doctors.map((option) => (
                            <option key={option.id} value={option.id}>{option.firstName + " " + option.lastName}</option>
                        ))}
                    </select>
                </label>
                <button>Se connecter</button>


                <p>Votre profil n'existe pas encore ?</p>
                <p>Créez votre compte :</p>
                <form>
                    <label>Prénom</label>
                    <input
                        name="firstName"
                        type="text"
                        value={this.state.newDoctor.firstName}
                        onChange={this.handleInputChange} />

                    <label>Nom</label>
                    <input
                        name="lastName"
                        type="text"
                        value={this.state.newDoctor.lastName}
                        onChange={this.handleInputChange} />

                    <label>Spécialité</label>
                    <select value={this.state.newDoctor.specialisation} onChange={this.onChange}>
                        {this.state.specialities.map(({key, value}) => (
                            <option key={key+1} value={value}>{value}</option>
                        ))}
                    </select>
                    <input type="submit" value="Submit" onClick={this.sendForm}></input>
                </form>
            </div>
        )
    }
}


