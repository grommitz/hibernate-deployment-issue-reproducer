package com.github.grommitz;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("/")
@Stateless
public class HelloResource {

	@PersistenceContext(unitName="thepu")
	private EntityManager em;

	List<String> names;
	Random random = new Random();

	@PostConstruct
	void init() {
		try {
			names = loadResourceLines("/names.txt");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	List<String> loadResourceLines(String resourceName) throws IOException {
		var is = this.getClass().getResourceAsStream(resourceName);
		if (is == null) {
			System.err.println("can't read " + resourceName);
			return List.of("Fred West", "Jeffrey Dahmer", "Hannibal Lecter", "Andrew Brodski");
		}

		List<String> data = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
			String strLine;
			while ((strLine = br.readLine()) != null) {
				data.add(strLine);
			}
		} finally {
			is.close();
		}
		return data;
	}

	@GET
	@Path("/{id}")
	public Response hello(@PathParam("id") long id) {
		System.out.println("loading id " + id + "...");
		var p = em.find(PersonEntity.class, id);
		if (p == null) {
			return Response.status(404).build();
		}
		return Response.ok("hello " + p.getName()).build();
	}

	@POST
	@Path("/create")
	public Response createPerson() throws URISyntaxException {
		var name = names.get(random.nextInt(names.size()));
		System.out.println("creating random person " + name);
		var p = new PersonEntity();
		p.setName(name);
		em.persist(p);
		System.out.println("Created " + p);
		return Response.status(201).location(new URI("/" + p.getId())).build();
	}


}