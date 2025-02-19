import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './index.css'
import Login from './screens/Login.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <Router>
    <Routes>
      <Route path='/' element={<Login />} />
    </Routes>
  </Router>
)
