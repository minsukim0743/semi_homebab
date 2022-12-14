package com.homebab.users.recipe.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.homebab.users.recipe.service.RecipeService;

@WebServlet("/recipe/delete")
public class DeleteRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 게시물 삭제를 위해 해당 게시물 인덱스를 받아옴.
		int rcpIdx = Integer.parseInt(request.getParameter("rcpIdx"));
		RecipeService recipeService = new RecipeService();
		
		int deleteRecipe = recipeService.deleteRecipe(rcpIdx);
		
		String path = "";
		
		if(deleteRecipe > 0) {
			
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteRecipe");
		} else {
			
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "레시피 게시물 삭제 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
