import { Link } from "react-router-dom";



function Home() {
    return (
        <div>
            <p>Vous êtes : </p>
            <Link to="/logindoctor">Docteur</Link> 
            <p> ou  </p> 
            <Link to="/logindoctor">Patient</Link>


        </div>
    );
  }
  
  export default Home;