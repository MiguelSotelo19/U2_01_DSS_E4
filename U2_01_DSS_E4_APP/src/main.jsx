import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ReactDOM from 'react-dom/client';
import { Login } from './screens/Login';
import { Gestion } from './screens/Gestion';
import { E403 } from './screens/403';
import { E404 } from './screens/404';

ReactDOM.createRoot(document.getElementById('root')).render(
  <Router>
    <Routes>
      <Route path='/' element={<Login />} />
      <Route path='/Gestion' element={<Gestion />} />
      <Route path='/E403' element={<E403 />} />
      <Route path='/E403' element={<E403 />} />
      <Route path='/404' element={<E404 />}/>
      <Route path="/*" element={<E404 />} />
    </Routes>
  </Router>
)
