import './App.css';
import Dasboard from './component/Dasboard';
import Header from './component/Layout/Header';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App">
      <Header /> 
      <Dasboard />
    </div>
  );
}

export default App;
