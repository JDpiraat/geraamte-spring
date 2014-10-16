package be.johan40.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.johan40.dao.EigenEntityDAO;
import be.johan40.entities.EigenEntity;

@Service
@Transactional
public class EigenEntityServiceImpl implements EigenEntityService {
	private final EigenEntityDAO eigenEntityDAO;
	@Autowired	
	EigenEntityServiceImpl(EigenEntityDAO eigenEntityDAO) {
	// Spring injecteert de parameter filiaalDAO
	// met de bean die de interface FiliaalDAO implementeert
	this.eigenEntityDAO = eigenEntityDAO;
	}
	
	@Override
	public Iterable<EigenEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
