import React from "react"
import './App.css';
import Home from './Components/Home'
import LogInDoctor from './Components/LogInDoctor'
import LogInPatient from './Components/LogInPatient'
import Doctor from './Components/Doctor'
import Patient from './Components/Patient'
import cors from 'cors'
import {
  BrowserRouter as Router,
  Route,
  Routes,
} from "react-router-dom";




function App() {
  return (
    
<Router>
      <p>Bienvenue sur doctolib !</p>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/logindoctor" element={<LogInDoctor/>} />
        <Route path="/loginpatient" element={<LogInPatient/>} />
        <Route path="/doctor" element={<Doctor/>} />
        <Route path="/patient" element={<Patient/>} />
      </Routes>
    </Router>
  );
}

export default App;
