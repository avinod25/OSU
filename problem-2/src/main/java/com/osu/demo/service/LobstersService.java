package com.osu.demo.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.osu.demo.api.model.LobsterInfo;
import com.osu.demo.api.model.LobsterRequest;
import com.osu.demo.common.DemoException;
import com.osu.demo.common.ErrorCode;
import com.osu.demo.model.Lobster;
import com.osu.demo.repository.LobsterRepository;

/**
 * The Class LobstersService.
 * 
 * @author Vinod Arroju
 */
@Service
public class LobstersService {
	
	/** The lobster repo. */
	@Autowired
	LobsterRepository lobsterRepo;
	
	/**
	 * List lobsters.
	 *
	 * @return the {@code List<LobsterInfo>}
	 */
	public List<LobsterInfo> listLobsters(){
		return lobsterRepo.findAllLobster();
	}

	/**
	 * Gets the lobster.
	 *
	 * @param lobsterId the {@code Integer}
	 * @return the {@code LobsterInfo}
	 * @throws DemoException the {@code DemoException}
	 */
	public LobsterInfo getLobster(Integer lobsterId) throws DemoException{
		try {
			return lobsterRepo.findLobsterById(lobsterId);
		}catch(Exception e) {
			throw new DemoException(ErrorCode.ServiceError,e);
		}
	}

	/**
	 * Delete lobster.
	 *
	 * @param lobsterId the {@code Integer}
	 * @return the boolean
	 * @throws DemoException the {@code DemoException}
	 */
	public Boolean deleteLobster(Integer lobsterId) throws DemoException{
		Boolean resp=false;
		try {
			if(lobsterRepo.existsById(lobsterId)) {
				lobsterRepo.deleteById(lobsterId);
				resp= true;
			}
		}catch(Exception e) {
			throw new DemoException(ErrorCode.ServiceError,e);
		}
		return resp;
		
	}

	/**
	 * Creates the lobster.
	 *
	 * @param request the {@code LobsterRequest}
	 * @param userDtls the {@code UserDetails}
	 * @return the {@code LobsterInfo}
	 * @throws DemoException the {@code DemoException}
	 */
	public LobsterInfo createLobster(LobsterRequest request, UserDetails userDtls) throws DemoException{
		LobsterInfo resp=null;
		try {
			if(request==null || StringUtils.isBlank(request.getName())) {
				throw new DemoException(ErrorCode.InputError,"Invalid request");
			}
			if(lobsterRepo.existsLobsterByNameIgnoreCase(request.getName().trim())) {
				throw new DemoException(ErrorCode.InputError,request.getName()+" lobster exists");
			}
			Lobster lobster=mapCreateRequest(request,userDtls);
			resp=mapToResponse(lobsterRepo.save(lobster));
		}catch(DemoException e) {
			throw e;
		}catch(Exception e) {
			throw new DemoException(ErrorCode.ServiceError,e);
		}
		return resp;
	}
	
	/**
	 * Map create request.
	 *
	 * @param request the {@code LobsterRequest}
	 * @param userDtls the {@code UserDetails}
	 * @return the {@code Lobster}
	 */
	private Lobster mapCreateRequest(LobsterRequest request,UserDetails userDtls) {
		Lobster lobster=new Lobster();
		lobster.setName(request.getName());
		lobster.setDescription(request.getDescription());
		lobster.setKind(request.getKind());
		lobster.setCreatedBy(userDtls.getUsername());
		lobster.setModifiedBy(userDtls.getUsername());
		lobster.setCreatedDate(new Date());
		lobster.setModifiedDate(new Date());
		return lobster;
	}
	
	/**
	 * Map to response.
	 *
	 * @param lobster the {@code Lobster}
	 * @return the {@code LobsterInfo}
	 */
	private LobsterInfo mapToResponse(Lobster lobster) {
		return new LobsterInfo(lobster.getLobsterId(),lobster.getName(),lobster.getDescription(),lobster.getKind());
	}

	/**
	 * Update lobster.
	 *
	 * @param lobsterId the {@code Integer}
	 * @param request the {@code LobsterRequest}
	 * @param userDtls the {@code UserDetails}
	 * @return the {@code LobsterInfo}
	 * @throws DemoException the {@code DemoException}
	 */
	public LobsterInfo updateLobster(Integer lobsterId, LobsterRequest request, UserDetails userDtls) throws DemoException{
		LobsterInfo resp=null;
		try {
			if(lobsterId!=null && request!=null) {
				Lobster dbLobster=lobsterRepo.findById(lobsterId).get();
				if(dbLobster!=null) {
					Lobster lobster=mapUpdateRequest(request,dbLobster,userDtls);
					resp=mapToResponse(lobsterRepo.save(lobster));
				}
			}else {
				throw new DemoException(ErrorCode.InputError,"Invalid request");
			}
		}catch(DemoException e) {
			throw e;
		}catch(Exception e) {
			throw new DemoException(ErrorCode.ServiceError,e);
		}
		return resp;
	}

	/**
	 * Map update request.
	 *
	 * @param request the {@code LobsterRequest}
	 * @param lobster the {@code Lobster}
	 * @param userDtls the {@code UserDetails}
	 * @return the  {@code Lobster}
	 */
	private Lobster mapUpdateRequest(LobsterRequest request,Lobster lobster, UserDetails userDtls) {
		lobster.setName(request.getName());
		lobster.setDescription(request.getDescription());
		lobster.setKind(request.getKind());
		lobster.setModifiedBy(userDtls.getUsername());
		lobster.setModifiedDate(new Date());
		return lobster;
	}
}
